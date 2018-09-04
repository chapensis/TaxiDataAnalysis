package com.yangchang.TaxiDataAnalysis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Order(1) // priority
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("MyFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String remoteAddr = servletRequest.getRemoteAddr();
        log.info("MyFilter doFilter ... remoteAddr:" + remoteAddr);
        // give it to next filter
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.info("MyFilter destroy...");
    }
}
