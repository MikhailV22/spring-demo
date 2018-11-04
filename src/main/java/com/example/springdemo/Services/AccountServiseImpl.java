package com.example.springdemo.Services;

import com.example.springdemo.Entity.Account;
import com.example.springdemo.Repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiseImpl implements AccountServise {
    @Autowired
    AccountRepository accountRepository;

    public List<Account> findAccountByNameAndPassword(String name, String password){
        return accountRepository.findAccountByNameAndPassword(name,password);
    }
}
