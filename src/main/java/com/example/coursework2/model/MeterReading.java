package com.example.coursework2.model;


import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "meterReading")
public class MeterReading {

    @Id
    @Column(name = "meterReading_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "electricity_mr_day")
    private double electricityMrDay;

    @Column(name = "electricity_mr_night")
    private double electricityMrNight;

    @Column(name = "gas_meter_reading")
    private double gasMeterReading;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private User user;

    @OneToMany(mappedBy = "meterReading",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bill> bill;


    public MeterReading() {
    }

    public MeterReading(Long id, LocalDate date, double electricityMrDay, double electricityMrNight, double gasMeterReading, User user, List<Bill> bill) {
        this.id = id;
        this.date = date;
        this.electricityMrDay = electricityMrDay;
        this.electricityMrNight = electricityMrNight;
        this.gasMeterReading = gasMeterReading;
        this.user = user;
        this.bill = bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getElectricityMrDay() {
        return electricityMrDay;
    }

    public void setElectricityMrDay(double electricityMrDay) {
        this.electricityMrDay = electricityMrDay;
    }

    public double getElectricityMrNight() {
        return electricityMrNight;
    }

    public void setElectricityMrNight(double electricityMrNight) {
        this.electricityMrNight = electricityMrNight;
    }

    public double getGasMeterReading() {
        return gasMeterReading;
    }

    public void setGasMeterReading(double gasMeterReading) {
        this.gasMeterReading = gasMeterReading;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Bill> getBill() {
        return bill;
    }

    public void setBill(List<Bill> bill) {
        this.bill = bill;
    }
}
