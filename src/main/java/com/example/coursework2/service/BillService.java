package com.example.coursework2.service;

import com.example.coursework2.model.Bill;
import com.example.coursework2.model.MeterReading;
import com.example.coursework2.repository.BillRepository;
import com.example.coursework2.repository.MeterReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    public List<Bill> listAll(){
        return billRepository.findAll();
    }

    public void save(Bill b) {billRepository.save(b);}

    public Bill get(long id){ return billRepository.findById(id).get();}

    public void delete(long id) { billRepository.deleteById(id);}


}
