package com.example.springdemo.Services;

public interface SecurityService {
    String findLoggedInUsername();
    void autoLogin(String username, String password);
}
