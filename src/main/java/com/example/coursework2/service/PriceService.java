package com.example.coursework2.service;

import com.example.coursework2.model.Price;
import com.example.coursework2.repository.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public List<Price> listAll(){
        return priceRepository.findAll();
    }

    public void save(Price pr) {priceRepository.save(pr);}

    public Price get(long id){ return priceRepository.findById(id).get();}

    public void delete(long id) { priceRepository.deleteById(id);}



}
