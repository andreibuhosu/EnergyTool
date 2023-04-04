package com.example.coursework2.controller;

import com.example.coursework2.model.MeterReading;
import com.example.coursework2.repository.MeterReadingRepository;
import com.example.coursework2.service.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class meterReadingsController {

    @Autowired
    private MeterReadingRepository meterReadingRepository;


    @GetMapping("/MeterReadings")
    public String manageCarPark(Model model){


        List<MeterReading> listmr = meterReadingRepository.findAll();
        model.addAttribute("listmr", listmr);





        return "meterReadings";
    }



}
