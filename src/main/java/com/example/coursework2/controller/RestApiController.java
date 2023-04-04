package com.example.coursework2.controller;

import com.example.coursework2.model.MeterReading;
import com.example.coursework2.model.User;
import com.example.coursework2.pojo.EnergyUsageStatistics;
import com.example.coursework2.repository.MeterReadingRepository;
import com.example.coursework2.repository.UserRepository;
import com.example.coursework2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/igse")
public class RestApiController {

    private final UserService userService;

    @Autowired
    private MeterReadingRepository meterReadingRepository;

    @Autowired
    public RestApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/propertycount")
    public List<Map<String, String>> getPropertiesByType() {
        Map<String, Long> propertyTypes = userService.getPropertiesByType();
        List<Map<String, String>> res = new ArrayList<>();
        for (Map.Entry<String, Long> entry : propertyTypes.entrySet()) {
            Map<String, String> map = new HashMap<>();
            map.put(entry.getKey(), String.valueOf(entry.getValue()));
            res.add(map);
        }
        return res;
    }

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/semi-detached/{bedrooms}")
    public EnergyUsageStatistics getEnergyUsageStatistics(@PathVariable int bedrooms) {
        // Find all 3 bedroom semi-detached houses
        List<User> semiDetachedHouses = userRepository.findByPropertyTypeAndNumberOfBedrooms("semi-detached", bedrooms);

        double totalElectricityCost = 0;
        double totalGasCost = 0;

        for (User house : semiDetachedHouses) {
            // Find all meter readings for the current house
            List<MeterReading> meterReadings = meterReadingRepository.findByUser(house);

            // Calculate total electricity cost and total gas cost for the house
            for (MeterReading meterReading : meterReadings) {
                totalElectricityCost += meterReading.getElectricityMrDay() + meterReading.getElectricityMrNight();
                totalGasCost += meterReading.getGasMeterReading();
            }
        }

        double averageElectricityGasCostPerDay = (totalElectricityCost + totalGasCost) / semiDetachedHouses.size() / 365;

        EnergyUsageStatistics statistics = new EnergyUsageStatistics();
        statistics.setType("semi-detached");
        statistics.setBedrooms(bedrooms);
        statistics.setAverageElectricityGasCostPerDay(averageElectricityGasCostPerDay);
        statistics.setUnit("pound");
        return statistics;
    }

}





