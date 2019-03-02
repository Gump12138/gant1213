package com.gm.gant1213.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CustomWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor( new UserInterceptor() ).addPathPatterns("/index");
        registry.addInterceptor( new findexInterceptor() ).addPathPatterns( "/findex","/ftable","/fblank" );
    }
}
