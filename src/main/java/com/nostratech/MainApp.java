package com.nostratech;

import java.util.*;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParkingLot parkingLot = null;

        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            if (parts[0].equals("create_parking_lot")) {
                String parkingLotId = parts[1];
                int numFloors = Integer.parseInt(parts[2]);
                int numSlotsPerFloor = Integer.parseInt(parts[3]);
                ParkingFloor[] floors = new ParkingFloor[numFloors];
                parkingLot = new ParkingLot(parkingLotId, numFloors, numSlotsPerFloor);
                System.out.println("Created parking lot with " + numFloors + " floors and " + numSlotsPerFloor + " slots per floor");
            } else if (parts[0].equals("park_vehicle")) {
                String vehicleType = parts[1];
                String regNo = parts[2];
                String color = parts[3];
                if (parkingLot != null) {
                    boolean parked = parkingLot.parkVehicle(new Vehicle(vehicleType, regNo, color));
                    if (!parked) {
                        System.out.println("Parking Lot Full");
                    }
                }
            } else if (parts[0].equals("unpark_vehicle")) {
                String ticketId = parts[1];
                if (parkingLot != null) {
                    parkingLot.unparkVehicle(ticketId);
                }
            } else if (parts[0].equals("display")) {
                String displayType = parts[1];
                String vehicleType = parts[2];
                if (parkingLot != null) {
                    if (displayType.equals("free_count")) {
                        parkingLot.displayFreeCount(vehicleType);
                    } else if (displayType.equals("free_slots")) {
                        parkingLot.displayFreeSlots(vehicleType);
                    } else if (displayType.equals("occupied_slots")) {
                        parkingLot.displayOccupiedSlots(vehicleType);
                    }
                }
            } else if (parts[0].equals("exit")) {
                break;
            }
        }
    }
}