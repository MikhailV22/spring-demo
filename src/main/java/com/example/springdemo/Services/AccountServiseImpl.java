package com.example.springdemo.Services;

import com.example.springdemo.Entity.Account;
import com.example.springdemo.Entity.Role;
import com.example.springdemo.Repo.AccountRepository;
import com.example.springdemo.Repo.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountServiseImpl implements AccountServise {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Account> findAccountByNameAndPassword(String name, String password){
        return accountRepository.findAccountByNameAndPassword(name,password);
    }

    @Override
    public void save(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findById(Integer.valueOf(1)).get());
        account.setRoles(roles);
        accountRepository.save(account);

    }

    @Override
    public Account findAccountByName(String name) {
        return accountRepository.findAccountByName(name);
    }
}
