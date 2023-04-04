package com.example.coursework2.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Price {

    @Id
    @Column(name = "price_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "electricity_price_day")
    private double electricityPriceDay;

    @Column(name = "electricity_price_night")
    private double electricityPriceNight;

    @Column(name = "gas_price")
    private double gasPrice;

    @Column(name = "standing_charge")
    private double standingCharge;

    @OneToMany(mappedBy = "price",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bill> bill;

    public Price() {
    }

    public Price(Long id, double electricityPriceDay, double electricityPriceNight, double gasPrice, double standingCharge, List<Bill> bill) {
        this.id = id;
        this.electricityPriceDay = electricityPriceDay;
        this.electricityPriceNight = electricityPriceNight;
        this.gasPrice = gasPrice;
        this.standingCharge = standingCharge;
        this.bill = bill;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getElectricityPriceDay() {
        return electricityPriceDay;
    }

    public void setElectricityPriceDay(double electricityPriceDay) {
        this.electricityPriceDay = electricityPriceDay;
    }

    public double getElectricityPriceNight() {
        return electricityPriceNight;
    }

    public void setElectricityPriceNight(double electricityPriceNight) {
        this.electricityPriceNight = electricityPriceNight;
    }

    public double getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(double gasPrice) {
        this.gasPrice = gasPrice;
    }

    public double getStandingCharge() {
        return standingCharge;
    }

    public void setStandingCharge(double standingCharge) {
        this.standingCharge = standingCharge;
    }

    public List<Bill> getBill() {
        return bill;
    }

    public void setBill(List<Bill> bill) {
        this.bill = bill;
    }
}
