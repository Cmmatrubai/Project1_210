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
            System.out.println("Invalid date");
            return;
        }
        if(dob.before(Date.today())) {
            System.out.println("Date not in the past!");
        }
        Make make = Make.valueOf(makeStr.toUpperCase());
        if(make == null) {
            System.out.println("Invalid make");
            return;
        }
        int mileage = Integer.parseInt(mileageStr);
        if(mileage < 0) {
            System.out.println("Invalid mileage");
        }
        if(fleet.contains(new Vehicle(plate, dob, make, mileage))) {
            System.out.println("Vehicle already exists");
            return;
        }
        fleet.add(new Vehicle(plate, dob, make, mileage));
        System.out.println("Vehicle added successfully");
    }
}

