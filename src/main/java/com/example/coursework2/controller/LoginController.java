package com.example.coursework2.controller;

import com.example.coursework2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {


    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public String login() {
        return "login";
    }


}
