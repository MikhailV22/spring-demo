package com.example.springdemo.Services;

import com.example.springdemo.Entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Component
public class UserInfo {
//    @Autowired
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public UserInfo (Account account){
        this.account=account;
        System.out.println("userInfo.created");
    }

}
