package com.example.coursework2.controller;

import com.example.coursework2.model.Price;
import com.example.coursework2.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class PricesController {
    
    @Autowired
    private PriceService priceService;

    @GetMapping("/prices")
    public String manageCarPark(Model model){
        List<Price> prices = priceService.listAll();
        model.addAttribute("prices", prices);

        return "prices";
    }

    @RequestMapping(value = "/savePR", method = RequestMethod.POST)
    public String savePrice(@ModelAttribute("prices") Price pr){
        priceService.save(pr);
        return "redirect:/prices";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditPrices(@PathVariable(name = "id") long id){
        ModelAndView mav1 = new ModelAndView("editPrices");
        Price pr = priceService.get(id);
        mav1.addObject("prices", pr);
        return mav1;
    }


}
