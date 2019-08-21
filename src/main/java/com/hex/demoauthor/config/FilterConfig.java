package com.hex.demoauthor.config;

import com.hex.demoauthor.aspect.AuthorFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类
 * User: hexuan
 * Date: 2019/8/21
 * Time: 1:44 PM
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new AuthorFilter());
        registration.addUrlPatterns("/*");
        registration.setName("AuthorFilter");
        registration.setOrder(1);
        return registration;
    }

}