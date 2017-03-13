package com.xs.config;

import com.xs.interceptor.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Created by xiaosong on 2017/3/12.
 */
//@Configuration
public class MyWebAppConfigurer  extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
     //   registry.addInterceptor(new MyInterceptor()).addPathPatterns("/login"); //拦截登录
        super.addInterceptors(registry);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MyMappingJackson2HttpMessageConverter());
      //  super.configureMessageConverters(converters);
    }
}
