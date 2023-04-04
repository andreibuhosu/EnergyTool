package com.example.coursework2;

import com.example.coursework2.model.MeterReading;
import com.example.coursework2.model.Price;
import com.example.coursework2.model.User;
import com.example.coursework2.model.Voucher;
import com.example.coursework2.repository.MeterReadingRepository;
import com.example.coursework2.repository.PriceRepository;
import com.example.coursework2.repository.UserRepository;
import com.example.coursework2.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;


@SpringBootApplication
public class Coursework2Application implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VoucherRepository voucherRepository;

    @Autowired
    private PriceRepository priceRepository;
    @Autowired
    private MeterReadingRepository meterReadingRepository;


    public static void main(String[] args) {
        SpringApplication.run(Coursework2Application.class, args);
    }


    @Override
    public void run(ApplicationArguments args) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


        //Users

        User user = new User();
        user.setId(1L);
        user.setUsername("test@gmail.com");
        user.setPassword(passwordEncoder.encode("12345"));
        user.setAddress("10 Clove St");
        user.setPropertyType("semi-detached");
        user.setNumberOfBedrooms(3);
        user.setBalance(0);
        user.setRole("Customer");
        userRepository.save(user);

        User admin = new User();
        admin.setUsername("gse@shangrila.gov.un");
        admin.setPassword(passwordEncoder.encode("gse@energy"));
        admin.setAddress("8 Claxon St");
        admin.setPropertyType("mansion");
        admin.setNumberOfBedrooms(12);
        admin.setBalance(0);
        admin.setRole("Admin");
        userRepository.save(admin);

        //Vouchers

        Voucher voucher = new Voucher();
        voucher.setCode("XTX2GZAD");
        voucher.setValue(200);
        voucher.setUsed(false);
        voucherRepository.save(voucher);

        Voucher voucher2 = new Voucher();
        voucher2.setCode("NDA7SY2V");
        voucher2.setValue(200);
        voucher2.setUsed(false);
        voucherRepository.save(voucher2);

        Voucher voucher3 = new Voucher();
        voucher3.setCode("RVA7DZ2D");
        voucher3.setValue(200);
        voucher3.setUsed(false);
        voucherRepository.save(voucher3);

        Voucher voucher4 = new Voucher();
        voucher4.setCode("DM8LEESR");
        voucher4.setValue(200);
        voucher4.setUsed(false);
        voucherRepository.save(voucher4);

        //Default Meter Prices

        Price price = new Price();
        price.setId(1L);
        price.setElectricityPriceDay(0.34);
        price.setElectricityPriceNight(0.2);
        price.setGasPrice(0.1);
        price.setStandingCharge(0.74);
        priceRepository.save(price);





    }

}





