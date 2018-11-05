package com.example.springdemo.Config;

import com.example.springdemo.Beans.Test;
import com.example.springdemo.Interceptors.LoggerInterceptor;
import com.example.springdemo.Services.AccountServise;
import com.example.springdemo.Services.AccountServiseImpl;
import com.example.springdemo.Services.UserInfo;
import com.example.springdemo.Entity.Account;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public Test test() {
        Test test = new Test();
        test.setId(10);
        test.setText("this is text");
        return test;
    }

    @Bean
//    @SessionScope
    @RequestScope
//    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.INTERFACES)
    public Account account() {
        Account account = new Account();
        account.setName("from config");
        account.setId(0);
        account.setPassword("123");
        System.out.println("account.created");
        return account;
    }


    @Bean
//    @RequestScope
    @SessionScope
    public UserInfo userInfo(Account account){
        UserInfo userInfo = new UserInfo(account);
//        userInfo.setAccount(account);
        System.out.println("userInfo.created");
        return userInfo;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggerInterceptor());
    }

//    @Bean
//    public AccountServise accountServise(){
//        return new AccountServiseImpl();
//    }

}
