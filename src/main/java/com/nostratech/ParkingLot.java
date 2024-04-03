package com.nostratech;

class ParkingLot {
    private String parkingLotId;
    private ParkingFloor[] floors;
    private int numSlotsPerFloor;

    public ParkingLot(String parkingLotId, int numFloors, int numSlotsPerFloor) {
        this.parkingLotId = parkingLotId;
        this.floors = new ParkingFloor[numFloors];
        this.numSlotsPerFloor = numSlotsPerFloor;
        for (int i = 0; i < numFloors; i++) {
            floors[i] = new ParkingFloor(i + 1, numSlotsPerFloor - 3, 2, 1, null); 
        }
        for (ParkingFloor floor : floors) {
            floor.setFloors(floors);
        }
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for (int i = 0; i < floors.length; i++) {
            int slotNo = floors[i].parkVehicle(vehicle);
            if (slotNo != -1) {
                String ticketId = generateTicketId(vehicle, i + 1, slotNo);
                System.out.println("Parked vehicle. Ticket ID: " + ticketId);
                return true;
            }
        }
        return false; 
    }


    public void unparkVehicle(String ticketId) {
        boolean unparked = false;
        if (ticketId.startsWith("Invalid")) {
            System.out.println("Invalid Ticket");
            return;
        }
        for (ParkingFloor floor : floors) {
            unparked = floor.unparkVehicle(ticketId);
            if (unparked) {
                return;
            }
        }
    }
    public void displayFreeCount(String vehicleType) {
        for (ParkingFloor floor : floors) {
            System.out.println("No. of free slots for " + vehicleType + " on Floor " + floor.getFloorNo() + ": " + floor.getFreeSlotsCount(vehicleType));
        }
    }

    public void displayFreeSlots(String vehicleType) {
        for (ParkingFloor floor : floors) {
            int[] freeSlots = floor.getFreeSlots(vehicleType);
            System.out.print("Free slots for " + vehicleType + " on Floor " + floor.getFloorNo() + ": ");
            for (int i = 0; i < freeSlots.length; i++) {
                if (i == freeSlots.length - 1) {
                    System.out.print(freeSlots[i]);
                } else {
                    System.out.print(freeSlots[i] + ",");
                }
            }
            System.out.println();
        }
    }

    public void displayOccupiedSlots(String vehicleType) {
        for (ParkingFloor floor : floors) {
            String occupiedSlots = floor.getOccupiedSlots(vehicleType);
            System.out.println("Occupied slots for " + vehicleType + " on Floor " + floor.getFloorNo() + ": " + occupiedSlots);
        }
    }

    private String generateTicketId(Vehicle vehicle, int floorNo, int slotNo) {
    	return parkingLotId + "_" + floorNo + "_" + slotNo; 
    }
}