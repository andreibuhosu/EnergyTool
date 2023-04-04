package com.example.coursework2.repository;

import com.example.coursework2.model.MeterReading;
import com.example.coursework2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MeterReadingRepository extends JpaRepository<MeterReading, Long> {
    @Query("SELECT AVG(e.electricityMrDay) FROM MeterReading e")
    Double[] getFieldAverages1();

    @Query("SELECT AVG(e.electricityMrNight)FROM MeterReading e")
    Double[] getFieldAverages2();

    @Query("SELECT AVG(e.gasMeterReading) FROM MeterReading e")
    Double[] getFieldAverages3();

    List<MeterReading> findByUser(User user);

    MeterReading findTopByOrderByIdDesc();

}
