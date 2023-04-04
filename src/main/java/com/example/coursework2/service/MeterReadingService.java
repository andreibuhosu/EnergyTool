package com.example.coursework2.service;

import com.example.coursework2.model.MeterReading;
import com.example.coursework2.repository.MeterReadingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterReadingService {

    @Autowired
    private MeterReadingRepository meterReadingRepository;

    public List<MeterReading> listAll(){
        return meterReadingRepository.findAll();
    }

    public void save(MeterReading mr) {meterReadingRepository.save(mr);}

    public MeterReading get(long id){ return meterReadingRepository.findById(id).get();}

    public void delete(long id) { meterReadingRepository.deleteById(id);}

    public MeterReading getPreviousReading() {
        // retrieve the previous reading from the repository, for example using a query
        return meterReadingRepository.findTopByOrderByIdDesc();
    }


}
