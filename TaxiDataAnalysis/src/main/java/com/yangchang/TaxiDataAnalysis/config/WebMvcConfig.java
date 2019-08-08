package com.yangchang.TaxiDataAnalysis.config;

import com.yangchang.TaxiDataAnalysis.controller.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author yangchang
 * 添加拦截器链
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    /**
     * 这样就能在拦截器中也注入对象了
     *
     * @return
     */
    @Bean
    public HandlerInterceptor getControllerInterceptor() {
        return new ControllerInterceptor();
    }

    /**
     * 添加注入了对象的拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getControllerInterceptor()).addPathPatterns("/*/**");
        super.addInterceptors(registry);
    }
}
