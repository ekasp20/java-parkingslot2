package com.nostratech;

class Vehicle {
    private String type;
    private String regNo;
    private String color;

    public Vehicle(String type, String regNo, String color) {
        this.type = type;
        this.regNo = regNo;
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public String getRegNo() {
        return regNo;
    }

    public String getColor() {
        return color;
    }
}
