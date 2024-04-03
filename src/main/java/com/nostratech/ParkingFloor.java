package com.nostratech;

class ParkingFloor {
    private int floorNo;
    private int numCarSlots;
    private int numBikeSlots;
    private int numTruckSlots;
    private ParkingSlot[] slots;
    private ParkingFloor[] floors;


    public ParkingFloor(int floorNo, int numCarSlots, int numBikeSlots, int numTruckSlots, ParkingFloor[] floors) {
        this.floorNo = floorNo;
        this.numCarSlots = numCarSlots;
        this.numBikeSlots = numBikeSlots;
        this.numTruckSlots = numTruckSlots;
        int totalSlots = numCarSlots + numBikeSlots + numTruckSlots;
        this.slots = new ParkingSlot[totalSlots];
        int index = 0;
        for (int i = 0; i < numTruckSlots; i++) {
            this.slots[index++] = new ParkingSlot("TRUCK");
        }
        for (int i = 0; i < numBikeSlots; i++) {
            this.slots[index++] = new ParkingSlot("BIKE");
        }
        for (int i = 0; i < numCarSlots; i++) {
            this.slots[index++] = new ParkingSlot("CAR");
        }
    }
    
    public void setFloors(ParkingFloor[] floors) {
        this.floors = floors;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public ParkingSlot[] getSlots() {
        return slots;
    }

    public int getFreeSlotsCount(String type) {
        int count = 0;
        for (ParkingSlot slot : slots) {
            if (slot != null && !slot.isOccupied() && slot.getType().equals(type)) {
                count++;
            }
        }
        return count;
    }

    public int[] getFreeSlots(String type) {
        int[] freeSlots = new int[slots.length];
        int count = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && !slots[i].isOccupied() && slots[i].getType().equals(type)) {
                freeSlots[count++] = i + 1;
            }
        }
        int[] trimmedArray = new int[count];
        System.arraycopy(freeSlots, 0, trimmedArray, 0, count);
        return trimmedArray;
    }

    public String getOccupiedSlots(String type) {
        StringBuilder occupiedSlots = new StringBuilder();
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].isOccupied() && slots[i].getType().equals(type)) {
                occupiedSlots.append(i + 1).append(",");
            }
        }
        if (occupiedSlots.length() > 0) {
            occupiedSlots.deleteCharAt(occupiedSlots.length() - 1);
        }
        return occupiedSlots.toString();
    }

    public int parkVehicle(Vehicle vehicle) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && !slots[i].isOccupied() && slots[i].getType().equals(vehicle.getType())) {
                slots[i].parkVehicle(vehicle);
                return i + 1; 
            }
        }
        return -1; 
    }
    
    public boolean unparkVehicle(String ticketId) {
        String[] parts = ticketId.split("_");
        if (parts.length != 3) {
            System.out.println("Invalid Ticket: Incorrect format");
            return false;
        }
        int floorNo;
        int slotNo;
        try {
            floorNo = Integer.parseInt(parts[1]);
            slotNo = Integer.parseInt(parts[2]);
        } catch (NumberFormatException e) {
            System.out.println("Invalid Ticket");
            return false;
        }
        if (floorNo <= 0 || floorNo > floors.length) {
            System.out.println("Invalid Ticket");
            return false;
        }
        ParkingFloor floor = floors[floorNo - 1];
        if (slotNo <= 0 || slotNo > floor.getSlots().length) {
            System.out.println("Invalid Ticket");
            return false;
        }
        ParkingSlot slot = floor.getSlots()[slotNo - 1];
        if (slot == null || !slot.isOccupied()) {
            return false;
        }
        Vehicle unparkedVehicle = slot.getOccupiedVehicle();
        if (unparkedVehicle != null) {
            slot.vacate();
            System.out.println("Unparked vehicle with Registration Number: " + unparkedVehicle.getRegNo() + " and Color: " + unparkedVehicle.getColor());
            return true;
        } else {
            System.out.println("Invalid Ticket: Vehicle not found");
            return false;
        }
    }
}
