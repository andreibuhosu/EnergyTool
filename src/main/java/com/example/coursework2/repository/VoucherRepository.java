package com.example.coursework2.repository;

import com.example.coursework2.model.Voucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, Long>{


    Voucher findByCode(String code);
}
