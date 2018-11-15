package com.example.springdemo.Controllers;

import com.example.springdemo.Entity.Account;
import com.example.springdemo.Services.AccountServise;
import com.example.springdemo.Services.SecurityService;
import com.example.springdemo.Validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController {
    @Autowired
    private AccountServise accountServise;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration",method = RequestMethod.GET)
    public String registration(Model model){
        model.addAttribute("userForm",new Account());
        return "registration";
    }

    @RequestMapping(value = "/registration",method = RequestMethod.Post)
    public String registration(@ModelAttribute("userForm") Account userForm, BindingResult bindingResult, Model model){
        userValidator.validate(userForm,bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }

        accountServise.save(userForm);
        securityService.autoLogin(userForm.getName(),userForm.getPassword());
        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login2",method = RequestMethod.GET)
    public String login(Model model, String error, String logout){
        if(error!=null){
            model.addAttribute("error","Username or password is incorrect");
        }

        if(logout!=null){
            model.addAttribute("message","Logout ok");
        }
        return "login2";
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(Model model){
        return "welcome";
    }

    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public String admin(Model model){
        return "admin";
    }

}

