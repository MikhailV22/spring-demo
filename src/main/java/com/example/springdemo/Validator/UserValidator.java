package com.example.springdemo.Validator;

import com.example.springdemo.Entity.Account;
import com.example.springdemo.Services.AccountServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private AccountServise accountServise;

    @Override
    public boolean supports(Class<?> aClass) {
        return Account.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Account user = (Account) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"username","Required");

    }
}
