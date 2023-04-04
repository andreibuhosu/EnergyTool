package com.example.coursework2.model;


import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "number_of_bedrooms")
    private int numberOfBedrooms;

    @Column(name = "balance")
    private double balance;

    @Column(name = "role")
    private String role = "Customer";

    @Column(name = "enabled")
    private boolean enabled = true;

    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MeterReading> meterReading;




    public User() {
        this.role = role;
    }

    public User(Long id, String username, String password, String address, String propertyType, int numberOfBedrooms, double balance, String role, boolean enabled, List<MeterReading> meterReading) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.address = address;
        this.propertyType = propertyType;
        this.numberOfBedrooms = numberOfBedrooms;
        this.balance = balance;
        this.role = role;
        this.enabled = enabled;
        this.meterReading = meterReading;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(String propertyType) {
        this.propertyType = propertyType;
    }

    public int getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(int numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<MeterReading> getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(List<MeterReading> meterReading) {
        this.meterReading = meterReading;
    }
}