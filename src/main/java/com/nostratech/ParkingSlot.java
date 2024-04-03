package com.nostratech;

class ParkingSlot {
    private String type;
    private boolean occupied;
    private Vehicle vehicle;

    public ParkingSlot(String type) {
        this.type = type;
        this.occupied = false;
    }
    public Vehicle getOccupiedVehicle() {
        return vehicle;
    }
    
    public void parkVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
        occupy();
    }
    
    public String getType() {
        return type;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void occupy() {
        occupied = true;
    }

    public void vacate() {
        occupied = false;
    }
}

