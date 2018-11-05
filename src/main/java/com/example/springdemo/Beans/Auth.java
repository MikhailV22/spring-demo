package com.example.springdemo.Beans;

import com.example.springdemo.Entity.Account;
import com.example.springdemo.Services.AccountServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@SessionScope
@Component
public class Auth {
    @Autowired
    AccountServise accountServise;

    public Auth() {
        System.out.println("Auth.created");
    }

    public boolean validate(String name, String password){
        List<Account> accounts = accountServise.findAccountByNameAndPassword(name,password);
        return accounts.size()==1;
    }
}
