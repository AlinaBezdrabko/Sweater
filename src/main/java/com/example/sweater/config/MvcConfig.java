package com.example.sweater.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    // класс, который содержит конфигурацию нашего веб-слоя
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        // Spring предоставляет нам систему авторизации, нам нужно только ее активизировать
        // указываем, что нам нужен login-шаблон
    }

}