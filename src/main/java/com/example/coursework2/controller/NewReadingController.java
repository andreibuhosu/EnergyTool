package com.example.coursework2.controller;

import com.example.coursework2.model.Bill;
import com.example.coursework2.model.MeterReading;
import com.example.coursework2.model.Price;
import com.example.coursework2.model.User;
import com.example.coursework2.repository.BillRepository;
import com.example.coursework2.repository.MeterReadingRepository;
import com.example.coursework2.repository.PriceRepository;
import com.example.coursework2.repository.UserRepository;
import com.example.coursework2.service.MeterReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DecimalFormat;
import java.time.LocalDate;

@Controller
public class NewReadingController {

    @Autowired
    MeterReadingRepository meterReadingRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private MeterReadingService meterReadingService;




    @RequestMapping("/newReading")
    public String newMeterReading(Model model){
        model.addAttribute("meterReading", new MeterReading());

        return "newReading";
    }


    @PostMapping("/saveMR")
    public String createObject(@ModelAttribute MeterReading meterReading,
                               Authentication authentication, Model model
                               ) {

        if(meterReading.getElectricityMrDay() < 0.009
            && meterReading.getElectricityMrNight() <0.009
            && meterReading.getGasMeterReading() <0.009) {
            model.addAttribute("emptyReading", "Readings can't be empty");
            return "newReading";
        }
        MeterReading previousReading = meterReadingService.getPreviousReading();
        if (previousReading != null && meterReading.getElectricityMrDay() < previousReading.getElectricityMrDay()) {
            model.addAttribute("error", "Error! The new meter reading is less than the previous reading.");
            return "newReading";
        }


        Price price = priceRepository.getReferenceById(1L);
        double totalAmount = meterReading.getElectricityMrDay() * price.getElectricityPriceDay()
                                + meterReading.getElectricityMrNight() * price.getElectricityPriceNight()
                                + meterReading.getGasMeterReading() * price.getGasPrice();
//        Allow 2 decimals only
        DecimalFormat df = new DecimalFormat("#.##");
        double formattedNumber = Double.parseDouble(df.format(totalAmount));

        Bill bill = new Bill();
        bill.setFinalBill(formattedNumber);
        bill.setMeterReading(meterReading);
        bill.setPrice(price);

        // save the Bill object to the database
        billRepository.save(bill);

        User user = userRepository.findByUsername(authentication.getName());
        meterReading.setUser(user);
        meterReading.setDate(LocalDate.now());
        meterReadingRepository.save(meterReading);
        model.addAttribute("success","New reading successfully added!");
        return "newReadingSuccess";

    }





}
