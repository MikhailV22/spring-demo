package com.example.springdemo.Services;

import com.example.springdemo.Entity.Account;
import com.example.springdemo.Entity.Role;
import com.example.springdemo.Repo.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;


public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    private AccountRepository accountRepository;


    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Account account = accountRepository.findAccountByName(name);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role:account.getRoles()){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(account.getName(),account.getPassword(),grantedAuthorities);
    }
}
