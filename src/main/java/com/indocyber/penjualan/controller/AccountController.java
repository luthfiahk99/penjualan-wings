package com.indocyber.penjualan.controller;

import com.indocyber.penjualan.entity.Account;
import com.indocyber.penjualan.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/loginForm")
    public String loginForm(Model model){

        Account account = new Account();
        model.addAttribute("account", account);

        return "account/login-form";
    }

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("breadCrumbs", "");
        return "account/home-page";
    }
}
