package com.yangchang.TaxiDataAnalysis.config;

import com.yangchang.TaxiDataAnalysis.controller.interceptor.ControllerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerInterceptor()).addPathPatterns("/*/**");
        super.addInterceptors(registry);
    }
}
