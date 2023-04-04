package com.example.coursework2.controller;

import com.example.coursework2.model.User;
import com.example.coursework2.model.Voucher;
import com.example.coursework2.repository.UserRepository;
import com.example.coursework2.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class TopUpController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    VoucherRepository voucherRepository;



    @GetMapping("/topUp")
    public String showBalance(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByUsername(username);
        model.addAttribute("balance", user.getBalance());
        return "topUp";
    }



    @PostMapping("/apply-voucher")
    public String applyVoucher(@RequestParam("code") String code, Model model, Principal principal) {
        Voucher voucher = voucherRepository.findByCode(code);
        if (code.isEmpty()) {
            model.addAttribute("empty","Field can't be empty!");
            return "topUp";
        }
        if (voucher == null) {
            model.addAttribute("errorMessage", "Invalid voucher code");
            return "topUp";
        }
        if (voucher.getUsed() == true) {
            model.addAttribute("errorUsed", "Voucher already used");
            return "topUp";
        }



        User user = userRepository.findByUsername(principal.getName());
        double newBalance = user.getBalance() + voucher.getValue();
        user.setBalance(newBalance);
        voucher.setUsed(true);
        userRepository.save(user);

        model.addAttribute("successMessage", "Voucher applied successfully! Your new balance is £" + newBalance + " energy credits");
        return "topUp";
    }




}
