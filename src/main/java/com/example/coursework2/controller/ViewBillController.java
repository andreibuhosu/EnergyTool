package com.example.coursework2.controller;

import com.example.coursework2.model.*;
import com.example.coursework2.repository.BillRepository;
import com.example.coursework2.repository.MeterReadingRepository;
import com.example.coursework2.repository.PriceRepository;
import com.example.coursework2.repository.UserRepository;
import com.example.coursework2.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ViewBillController {

//    @RequestMapping("/viewBill")
//    public String viewBill() {
//        return "viewBill";
//    }

    @Autowired
    private MeterReadingRepository meterReadingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private BillRepository billRepository;

    @Autowired
    private BillService billService;


    @GetMapping("/viewBill")
    public String viewBill(Model model){
        List<Bill> listb = billService.listAll();
        model.addAttribute("listb", listb);
        return "viewBill";
    }

    @GetMapping("/pay/{billId}")
    public String payBill(@PathVariable("billId") long billId,Model model, Principal principal) {
        Bill bill = billRepository.findById(billId).orElse(null);
        User user = userRepository.findByUsername(principal.getName());
        if (bill.getFinalBill() > user.getBalance()) {
            model.addAttribute("errorMessage", "Not enough balance");
            return "viewBillStatus";
        }
        if (bill.getPaid().equals("Paid")) {
            model.addAttribute("errorUsed", "Bill is already paid");
            return "viewBillStatus";
        }


        double newBalance = user.getBalance() - bill.getFinalBill();
        user.setBalance(newBalance);
        bill.setPaid("Paid");
        userRepository.save(user);
        billRepository.save(bill);

        model.addAttribute("successMessage", "Bill successfully paid! Your remaining balance is £" + newBalance + " energy credits");
        return "viewBillStatus";
    }

}