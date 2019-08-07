package com.yangchang.TaxiDataAnalysis.config;

import com.yangchang.TaxiDataAnalysis.bean.CurrentUser;
import com.yangchang.TaxiDataAnalysis.bean.vo.UserVO;
import com.yangchang.TaxiDataAnalysis.service.UserService;
import com.yangchang.TaxiDataAnalysis.tools.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangchang
 * 过滤器有两种配置方式：
 * 方式一：@WebFilter(urlPatterns = "/*",filterName = "MyFilter")
 * 方式二：不添加WebFilter注解，通过@Bean注入spring，不添加WebFilter注解，通过@Bean注入spring
 * 配置过滤器，拦截用户请求，判断用户是否登陆
 */
@Slf4j
@Order(1) // priority
@WebFilter(urlPatterns = "/*", filterName = "MyFilter")
public class MyFilter implements Filter {
    private static final String PREFIX = "uni_token_";

    private static final String[] whileList = {"/service/test", "/service/user/"};

    @Autowired
    public RedissonClient redissonClient;

    @Autowired
    public UserService userService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String remoteAddr = servletRequest.getRemoteAddr();
        log.info("MyFilter doFilter ... remoteAddr:" + remoteAddr + " request url: " + request.getRequestURI());
        try {
            // 如果是登陆请求，则不做认证的拦截
            if (isUrlInWhileList(request.getRequestURI())) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else if (checkToken(request, response) && isAccessAllowed(request, response)) {
                // give it to next filter
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查请求的路径是否在白名单中
     *
     * @param url
     * @return
     */
    private boolean isUrlInWhileList(String url) {
        for (String noCheckUrl : whileList) {
            if (url.startsWith(noCheckUrl)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        log.info("MyFilter destroy...");
    }

    /**
     * 检查是否携带token 以及token是否失效
     *
     * @param request
     * @param response
     * @return
     */
    private boolean checkToken(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        log.info("checkToken token:" + token);
        // 在访问头部中，存放一个 Authorization的头，没有这个头就无权访问
        if (StringUtils.isBlank(token)) {
            sendJsonResponse(response, 50016, "您尚未登录，无权访问");
            return false;
        }

        // 校验token是否存在
        RBucket<UserVO> rBucket = redissonClient.getBucket(token);
        UserVO userVO = rBucket.get();
        // 如果存在就会得到UserVO
        if (userVO == null) {
            sendJsonResponse(response, 50015, "无效令牌，未通过认证");
            return false;
        }

        CurrentUser.put(userVO);
        return true;
    }

    /**
     * 判断是否允许访问
     *
     * @param request
     * @param response
     * @return
     */
    private boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) {
        String token = request.getHeader("Authorization");
        log.info("token:" + token);
        String username = JWTUtil.getUsername(token);
        String userKey = PREFIX + username;

        RBucket<String> bucket = redissonClient.getBucket(userKey);
        String redisToken = bucket.get();
        log.info("redisToken:" + redisToken);
        // 说明之前登陆的也是这个用户
        if (token.equals(redisToken)) {
            return true;
        } else if (StringUtils.isBlank(redisToken)) {
            // 说明这个用户第一次登陆
            bucket.set(token);
        } else {
            Long redisTokenUnixTime = JWTUtil.getClaim(redisToken, "createTime").asLong();
            Long tokenUnixTime = JWTUtil.getClaim(token, "createTime").asLong();

            // token > redisToken 则覆盖
            if (tokenUnixTime.compareTo(redisTokenUnixTime) > 0) {
                bucket.set(token);
            } else {
                // 如果当前请求的时间比redis中的旧，就说明有其他用户在其他浏览器登陆。注销当前旧token,下次再访问就没有token了
                userService.logout(token);
                sendJsonResponse(response, 50017, "您的账号已在其他设备登录，请重新登录");
                return false;
            }
        }
        return true;
    }

    private static void sendJsonResponse(HttpServletResponse response, int code, String message) {
        sendJsonResponse(response, String.format(jsonTemplate(), code, message));
    }

    private static String jsonTemplate() {
        return "{\"code\":%s,\"message\":\"%s\",\"data\":null}";
    }

    private static void sendJsonResponse(HttpServletResponse response, String json) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            out.append(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
