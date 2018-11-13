package com.example.springdemo.Config;

import com.example.springdemo.Beans.Test;
import com.example.springdemo.Interceptors.LoggerInterceptor;
import com.example.springdemo.Services.AccountServise;
import com.example.springdemo.Services.AccountServiseImpl;
import com.example.springdemo.Services.UserInfo;
import com.example.springdemo.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Test test() {
        Test test = new Test();
        test.setId(10);
        test.setText("this is text");
        return test;
    }

    @Bean
//    @SessionScope
//    @RequestScope
//    @Scope("prototype")
    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Account account() {
        Account account = new Account();
        account.setName("from config");
        account.setId(0);
        account.setPassword("123");
        System.out.println("account.created (AppConfig)");
        return account;
    }

    @Bean
//    @RequestScope
    @SessionScope
    public UserInfo userInfo(){
        UserInfo userInfo = new UserInfo(account());
//        UserInfo userInfo = new UserInfo();
//        userInfo.setAccount(account);
        System.out.println("userInfo.created (AppConfig)");
        return userInfo;
    }

//    @Bean
////    @RequestScope
//    @SessionScope
//    public UserInfo userInfo(Account account){
//        UserInfo userInfo = new UserInfo(account);
////        UserInfo userInfo = new UserInfo();
////        userInfo.setAccount(account);
//        System.out.println("userInfo.created");
//        return userInfo;
//    }

//    @Autowired
//    UserInfo userInfo;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("addInterceptors (AppConfig)");
//        registry.addInterceptor(new LoggerInterceptor(userInfo)).addPathPatterns("/accounts/**");
        registry.addInterceptor(new LoggerInterceptor(userInfo())).addPathPatterns("/accounts/**");
    }

//    @Bean
//    public AccountServise accountServise(){
//        return new AccountServiseImpl();
//    }

}
