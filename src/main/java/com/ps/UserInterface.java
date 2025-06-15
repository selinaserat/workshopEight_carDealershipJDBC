package com.ps;

import com.ps.data.*;
import com.ps.models.Vehicle;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private VehicleDAO vehicleDAO;
//    private final SalesContractDAO salesDAO;
//    private final LeaseContractDAO leaseDAO;
//    private final DealershipDAO dealershipDAO;

    public UserInterface(VehicleDAO vehicleDAO /* ,SalesContractDAO salesDAO,
                         LeaseContractDAO leaseDAO, DealershipDAO dealershipDAO */) {

        this.vehicleDAO = vehicleDAO;
//        this.salesDAO = salesDAO;
//        this.leaseDAO = leaseDAO;
//        this.dealershipDAO = dealershipDAO;
    }

    public void display() {
        int mainCommand;

        do {
            System.out.println("1) Get All Vehicles");
            System.out.println("2) Get By VIN");
            System.out.println("3) Create vehicle");
            System.out.println("4) Update vehicle");
            System.out.println("5) Delete vehicle");
            System.out.println("0) Exit");
            System.out.print("Option: ");

            mainCommand = scanner.nextInt();
            scanner.nextLine(); // flush

            switch (mainCommand) {
                case 1:
                    List<Vehicle> vehicles = vehicleDAO.getAll();
                    System.out.println(vehicles);
                    break;
                case 2:
                    System.out.print("VIN: ");
                    String vin = scanner.nextLine();
                    System.out.println(vehicleDAO.getByVin(vin));
                    break;
                case 3:
                    createVehicle();
                    break;
                case 4:
                    updateVehicle();
                    break;
                case 5:
                    System.out.print("VIN to delete: ");
                    String deleteVin = scanner.nextLine();
                    vehicleDAO.delete(deleteVin);
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid command");
            }
        } while (mainCommand != 0);
    }

    private void createVehicle() {
        System.out.print("VIN: ");
        String vin = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Sold (YES or NO): ");
        String sold = scanner.nextLine();

        Vehicle v = new Vehicle(vin, year, make, model, type, color, odometer, price, sold);
        vehicleDAO.create(v);
    }

    private void updateVehicle() {
        System.out.print("VIN to update: ");
        String vin = scanner.nextLine();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Sold (YES or NO): ");
        String sold = scanner.nextLine();

        Vehicle v = new Vehicle(vin, year, make, model, type, color, odometer, price, sold);
        vehicleDAO.update(vin, v);
    }
}
