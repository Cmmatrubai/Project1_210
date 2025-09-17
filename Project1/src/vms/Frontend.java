/**
 * Vehicle Management System - Project 1
 * @author Chaitanya Matrubai
**/

package vms;

import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Calendar;
public class Frontend {
    private final Fleet fleet = new Fleet();
    private final Reservation reservations = new Reservation();

    public void run() {
        System.out.println("Vehicle Management Software is running");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if(!scanner.hasNextLine()) {
                break;
            }
            String command = scanner.nextLine();
            if(command == null || command.isEmpty()) { 
                break;
            }
            command = command.trim();
            if(command.length() == 0) {
                continue;
            }
            char commandChar = command.charAt(0);
            switch(commandChar) {
                case 'A':
                    processAdd(command);
                    break;
                case 'D':
                    processDelete(command);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }
        }
        scanner.close();
    }
    private void processAdd(String command) {
        StringTokenizer tokenizer = new StringTokenizer(command);
        tokenizer.nextToken();
        String plate = tokenizer.nextToken();
        String dobStr = tokenizer.nextToken();
        String makeStr = tokenizer.nextToken();
        String mileageStr = tokenizer.nextToken();
        Date dob = Date.parse(dobStr);
        if(dob == null || !dob.isValid()) {
            System.out.println(dobStr + " - invalid calendar date.");
            return;
        }
        if(!dob.before(Date.today())) {
            System.out.println(dobStr + " - is today or a future date.");
            return;
        }
        Make make;
        try {
            make = Make.valueOf(makeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(makeStr + " - invalid make.");
            return;
        }
        int mileage = Integer.parseInt(mileageStr);
        if(mileage < 0) {
            System.out.println(mileageStr + " - invalid mileage.");
            return;
        }
        if(fleet.contains(new Vehicle(plate, dob, make, mileage))) {
            System.out.println("Vehicle already exists");
            return;
        }
        Vehicle vehicle = new Vehicle(plate, dob, make, mileage);
        fleet.add(vehicle);
        System.out.println(vehicle + " has been added to the fleet.");
    }
    private Make parseMake(String s) {
        if (s == null) return null;
        String u = s.trim().toUpperCase();
        try {
            return Make.valueOf(u);
        } catch (Exception e) {
            return null;
        }
    }
    private void processDelete(String line) {
        // D plate
        StringTokenizer st = new StringTokenizer(line);
        st.nextToken(); // D
        String plate = st.nextToken();
        Vehicle v = fleet.getByPlate(plate);
        if (v == null) { System.out.println("Vehicle not found!"); return; }
        // cannot delete if there are bookings for this vehicle
        if (reservations.vehicleUnavailable(plate, Date.today(), Date.today())) {
            System.out.println("Cannot delete: vehicle has bookings.");
            return;
        }
        fleet.remove(v);
        System.out.println("Vehicle removed.");
    }


}

