package com.example.log.logdemo;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SampleConfiguration {

    @Bean
    public FilterRegistrationBean<SampleFilter> hogeFilter() {
        FilterRegistrationBean<SampleFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new SampleFilter());
        bean.addUrlPatterns("/api/*");
        bean.setOrder(Integer.MIN_VALUE);
        return bean;
    }
}
