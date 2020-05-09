package com.zxz.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by limi on 2017/10/15.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/insertComment")
                .addPathPatterns("/insertReply")
        .addPathPatterns("/select_is_zan")
        .addPathPatterns("/add_zan")
        .addPathPatterns("/delete_zan");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/blog_img/**").addResourceLocations("file:F:/workspace/blog_file/blog_img/");
        super.addResourceHandlers(registry);
    }
}
