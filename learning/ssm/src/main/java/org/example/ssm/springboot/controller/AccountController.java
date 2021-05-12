package org.example.ssm.springboot.controller;

import org.example.ssm.domain.Account;
import org.example.ssm.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 账户控制器类
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping("/saveAccount")
    public void saveAccount(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("表现层：saveAccount...");
        System.out.println(account);
        accountService.saveAccount(account);
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return;
    }

    @RequestMapping("/findAll")
    public String findAll(Model model) {
        System.out.println("表现层：findAll...");
        List<Account> accounts = accountService.findAll();
        for (Account account: accounts) {
            System.out.println(account);
        }
        model.addAttribute("list", accounts);
        return "list";
    }
}
