package com.example.coursework2.model;

import javax.persistence.*;

@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @Column(name = "bill_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "final_bill")
    private double finalBill;

    @Column(name = "paid")
    private String paid= "Unpaid";

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "meterReading_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private MeterReading meterReading;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "price_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Price price;

    public Bill() {
    }

    public Bill(Long id, double finalBill, String paid, MeterReading meterReading, Price price) {
        this.id = id;
        this.finalBill = finalBill;
        this.paid = paid;
        this.meterReading = meterReading;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFinalBill() {
        return finalBill;
    }

    public void setFinalBill(double finalBill) {
        this.finalBill = finalBill;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public MeterReading getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(MeterReading meterReading) {
        this.meterReading = meterReading;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }
}
