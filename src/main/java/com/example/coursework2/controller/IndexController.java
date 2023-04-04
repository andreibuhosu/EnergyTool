package com.example.coursework2.controller;

import com.example.coursework2.model.User;
import com.example.coursework2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/indexCustomer")
    public String showBalance(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByUsername(username);
        model.addAttribute("balance", user.getBalance());
        return "indexCustomer";
    }

    @RequestMapping("/indexAdmin")
    public String Admin(){

        return "indexAdmin";
    }

}
