package com.example.coursework2.controller;

import com.example.coursework2.model.User;
import com.example.coursework2.model.Voucher;
import com.example.coursework2.repository.UserRepository;
import com.example.coursework2.repository.VoucherRepository;
import com.example.coursework2.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @Autowired
    private UserDetailsServiceImpl userService;



    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute User user, Model model) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPass = passwordEncoder.encode(user.getPassword());

        if(user.getUsername().isEmpty()){
            model.addAttribute("emptyUser","Email can't be empty");
            return "register";
        }
        if (userService.isUsernameTaken(user.getUsername())) {
            model.addAttribute("errorTaken", "Username already exists");
            return "register";
        }
        if(user.getPassword().isEmpty()){
            model.addAttribute("emptyPassword","Password can't be empty");
            return "register";
        }
        if(user.getAddress().isEmpty()) {
            model.addAttribute("emptyAddress", "Address can't be empty");
            return "register";
        }
        if(user.getNumberOfBedrooms() < 1){
            model.addAttribute("emptyNumberOfBedrooms","Enter a valid number of bedrooms");
            return "register";
        }


        userService.registerUser(user.getUsername(), passwordEncoder.encode(user.getPassword()),user.getAddress(),user.getPropertyType(),user.getNumberOfBedrooms(), (int) user.getBalance());

        return "redirect:/login";
    }



}
