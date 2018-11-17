package com.example.springdemo.Services;

import com.example.springdemo.Entity.Account;
import com.example.springdemo.Repo.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional
public class MyUserDetailsService  implements UserDetailsService {
    private static Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MyUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//log.info("bCryptPasswordEncoder."+bCryptPasswordEncoder.encode("123"));
log.info("MyUserDetailsService.loadUserByUsername");
        Account user = accountRepository.findAccountByName(username);
        user.getRoles().size();
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new MyUserPrincipal(user);
//log.info("account.name "+user.getName());
//        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//        for (Role role : user.getRoles()){
//            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//log.info("role "+role.getName());
//        }
//
//
//        return  new org.springframework.security.core.userdetails.User(user.getName(),user.getPassword(),grantedAuthorities);


    }
}