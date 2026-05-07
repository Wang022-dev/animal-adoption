package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/front/**")
                .addResourceLocations("classpath:/front/front/");
        registry.addResourceHandler("/admin/**")
                .addResourceLocations("classpath:/admin/admin/");
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("classpath:/static/upload/", "file:static/upload/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/login");
        registry.addViewController("/index.html").setViewName("redirect:/login");
    }
}
