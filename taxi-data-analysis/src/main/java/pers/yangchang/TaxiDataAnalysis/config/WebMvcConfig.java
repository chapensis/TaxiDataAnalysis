package pers.yangchang.TaxiDataAnalysis.config;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import pers.yangchang.TaxiDataAnalysis.controller.interceptor.ControllerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

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

    /**
     * 静态资源路由
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/static/");
        registry.addResourceHandler("/index.html").addResourceLocations("classpath:/static/index.html");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon.icol");
    }

    /**
     * 默认首页，可以指定到static、public下的某些页面
     * @param registry
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
