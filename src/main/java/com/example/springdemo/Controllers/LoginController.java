package com.example.springdemo.Controllers;

import com.example.springdemo.Beans.Auth;
import com.example.springdemo.Services.AccountServise;
import com.example.springdemo.Entity.Account;
import com.example.springdemo.Services.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
//    @Autowired
//    private AccountRepository accountRepository;

    @Autowired
    UserInfo userInfo;

    @Autowired
    private AccountServise accountServise;

    @Autowired
    private Auth auth;

    @Autowired
    private Account account;

    @GetMapping("/login")
    public ModelAndView login(Model model){

//        model.addAttribute("name","test-name");
        ModelAndView mv = new ModelAndView("login");
//        mv.addObject("name","test-name");
        mv.addObject("account", account);
//        mv.addObject("connectedUser", account);
        mv.addObject("userInfo", userInfo);
        return mv;
        //return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute Account account,
                          Model model){
//        account.setName("---");
        System.out.println("name."+account.getName());
//        userInfo.getAccount().setName(account.getName());
//        List<Account> accounts = accountServise.findAccountByNameAndPassword(account.getName(),account.getPassword());
//        System.out.println("size."+ accounts.size());

        if(!auth.validate(account.getName(),account.getPassword())){
            model.addAttribute("error","Неверный пароль");
//            model.addAttribute("connectedUser",this.account);
            model.addAttribute("userInfo", userInfo);
            return "login";
        }

        List<Account> accounts = accountServise.findAccountByNameAndPassword(account.getName(),account.getPassword());
//        this.account.setName(accounts.get(0).getName());
//        this.account.setPassword(accounts.get(0).getPassword());
//        this.account.setId(accounts.get(0).getId());

        userInfo.setAccount(accounts.get(0));

//        model.addAttribute("connectedUser",this.account);
//        model.addAttribute("password",password);
//        return "redirect:login";
        return "redirect:login";
    }


    @PostMapping("/doLogout")
    public String doLogout(HttpSession session){
        session.invalidate();
        return "redirect:login";
    }

//    @PostMapping("/doLogin")
//    public String doLogin(@RequestParam String name,
//                          @RequestParam String password,
//                          Model model){
//        System.out.println("name."+name);
//        model.addAttribute("name",name);
//        model.addAttribute("password",password);
////        return "redirect:login";
//        return "login";
//    }

}
