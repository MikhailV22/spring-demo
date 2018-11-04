package com.example.springdemo.Controllers;

import com.example.springdemo.Beans.Auth;
import com.example.springdemo.Services.AccountServise;
import com.example.springdemo.Services.AccountServiseImpl;
import com.example.springdemo.Entity.Account;
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
    private AccountServise accountServise;

    @Autowired
    private Auth auth;

    @Autowired
    private Account acount;

    @GetMapping("/login")
    public ModelAndView login(Model model){

//        model.addAttribute("name","test-name");
        ModelAndView mv = new ModelAndView("login");
//        mv.addObject("name","test-name");
        mv.addObject("account",acount);
        mv.addObject("connectedUser",acount);
        return mv;
        //return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(@ModelAttribute Account account,
                          Model model){
//        account.setName("---");
        System.out.println("name."+account.getName());

//        List<Account> accounts = accountServise.findAccountByNameAndPassword(account.getName(),account.getPassword());
//        System.out.println("size."+ accounts.size());

        if(!auth.validate(account.getName(),account.getPassword())){
            model.addAttribute("error","Неверный пароль");
            model.addAttribute("connectedUser",this.acount);
            return "login";
        }

        List<Account> accounts = accountServise.findAccountByNameAndPassword(account.getName(),account.getPassword());
        this.acount.setName(accounts.get(0).getName());
        this.acount.setPassword(accounts.get(0).getPassword());
        this.acount.setId(accounts.get(0).getId());

        model.addAttribute("connectedUser",this.acount);
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
