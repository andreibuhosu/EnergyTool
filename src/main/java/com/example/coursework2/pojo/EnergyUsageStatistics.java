package com.example.coursework2.pojo;

public class EnergyUsageStatistics {
    private String type;
    private int bedrooms;
    private double averageElectricityGasCostPerDay;
    private String unit;
    // getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getAverageElectricityGasCostPerDay() {
        return averageElectricityGasCostPerDay;
    }

    public void setAverageElectricityGasCostPerDay(double averageElectricityGasCostPerDay) {
        this.averageElectricityGasCostPerDay = averageElectricityGasCostPerDay;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}