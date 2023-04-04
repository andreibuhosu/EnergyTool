package com.example.coursework2.controller;

import com.example.coursework2.model.MeterReading;
import com.example.coursework2.repository.MeterReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class EnergyStatisticsController {

    @Autowired
    private MeterReadingRepository meterReadingRepository;



    @GetMapping("/energyStatistics")
    public String displayAverages(Model model) {
        List<MeterReading> meterReading = meterReadingRepository.findAll();

        double avgEle = 0;
        for (MeterReading meterReading1 : meterReading) {
            avgEle += meterReading1.getElectricityMrDay();
        }
        avgEle = avgEle / meterReading.size();
        model.addAttribute("AvgelectricityMrDay", avgEle);
        double avgEle1 = 0;
        for (MeterReading meterReading1 : meterReading) {
            avgEle1 += meterReading1.getElectricityMrNight();
        }
        avgEle1 = avgEle1 / meterReading.size();
        model.addAttribute("AvgelectricityMrNight", avgEle1);
        double avgG = 0;
        for (MeterReading meterReading1 : meterReading) {
            avgG += meterReading1.getGasMeterReading();
        }
        avgG = avgG / meterReading.size();
        model.addAttribute("AvggasMeterReading", avgG);

        return "energyStatistics";
    }


}
