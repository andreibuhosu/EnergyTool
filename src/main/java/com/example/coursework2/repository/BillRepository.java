package com.example.coursework2.repository;

import com.example.coursework2.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository <Bill, Long> {

}
