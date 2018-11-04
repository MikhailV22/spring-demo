package com.example.springdemo.Beans;

import com.example.springdemo.Entity.Account;
import com.example.springdemo.Services.AccountServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Auth {
    @Autowired
    AccountServise accountServise;

    public boolean validate(String name,String password){
        List<Account> accounts = accountServise.findAccountByNameAndPassword(name,password);
        return accounts.size()==1;
    }
}
