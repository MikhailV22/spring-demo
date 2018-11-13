package com.example.springdemo.Services;

import com.example.springdemo.Entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AccountServise {
    public List<Account> findAccountByNameAndPassword(String name, String password);
    void save(Account account);
    Account findAccountByName(String name);
}
