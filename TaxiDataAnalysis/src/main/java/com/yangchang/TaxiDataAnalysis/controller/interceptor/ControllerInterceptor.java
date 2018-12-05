package com.yangchang.TaxiDataAnalysis.controller.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class ControllerInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ControllerInterceptor preHandle ...");
        try {
            // 1、设置IP地址和端口
            Jedis jedis = new Jedis("127.0.0.1", 6379);

            // 2、保存数据
            jedis.incr("visits");
            log.info("当前访问次数：" + jedis.get("visits"));

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
