package pers.yangchang.TaxiDataAnalysis.controller.interceptor;

import pers.yangchang.TaxiDataAnalysis.bean.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangchang
 * 控制器层拦截器，主要统计访问次数，用在过滤器就不行了
 */
@Slf4j
public class ControllerInterceptor implements HandlerInterceptor {

    @Autowired
    private JedisPool jedisPool;

    /**
     * 当前的作用就是只是拦截统计访问次数
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ControllerInterceptor preHandle ...");
        try {
            // 1、设置IP地址和端口, 直接用连接池，不用每次都自己new啦!!!
//             Jedis jedis = new Jedis("127.0.0.1", 6379);
            Jedis jedis = jedisPool.getResource();

            // 2、保存数据
            jedis.incr("visits");
            log.info("记录当前访问次数：" + jedis.get(Constant.VISIT_COUNT));

            // 4、关闭连接
            jedis.close();
        } catch (Exception e) {
            log.error("jedis operate error:", e);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("ControllerInterceptor postHandle ..." + request.getRequestURI());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("ControllerInterceptor afterCompletion ...");
    }
}
