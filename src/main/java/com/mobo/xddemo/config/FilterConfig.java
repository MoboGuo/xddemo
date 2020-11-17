package com.mobo.xddemo.config;

import com.mobo.xddemo.filter.LoginFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.FilterRegistration;
import java.util.Collections;

/**
 * 过滤器配置
 *
 * @author Mobo
 * @create 2020-11-16-15:31
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<LoginFilter> filter1(){
        //通过FilterRegistrationBean实例设置优先级可以生效
        //通过@WebFilter无效
        FilterRegistrationBean<LoginFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new LoginFilter());
        bean.setName("loginFilter");
        bean.setOrder(1);
        bean.setUrlPatterns(Collections.singleton("/api/v1/pri/order/*"));
        return bean;
    }
}
