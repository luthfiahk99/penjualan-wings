package com.indocyber.penjualan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
        Locale indonesia = new Locale("id", "ID");
        sessionLocaleResolver.setDefaultLocale(indonesia);
        return sessionLocaleResolver;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("forward:/account/home");
        registry.addViewController("/product").setViewName("forward:/product/index");
        registry.addViewController("/checkoutPage").setViewName("forward:/product/checkoutPage");

    }
}
