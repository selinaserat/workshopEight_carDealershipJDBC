package com.ps;

import com.ps.data.*;
import com.ps.models.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner = new Scanner(System.in);
    private VehicleDAO vehicleDAO;
    private SalesContractDAO salesContractDAO;
    private LeaseContractDAO leaseContractDAO;
    private DealershipDAO dealershipDAO;

    public UserInterface(
            VehicleDAO vehicleDAO,
            SalesContractDAO salesContractDAO,
            LeaseContractDAO leaseContractDAO,
            DealershipDAO dealershipDAO
    )
    {
        this.vehicleDAO = vehicleDAO;
        this.salesContractDAO = salesContractDAO;
        this.leaseContractDAO = leaseContractDAO;
        this.dealershipDAO = dealershipDAO;
    }

    public void display() {
        System.out.println("\n" +  Ansi.ANSI_BRIGHT_MAGENTA + Ansi.BOLD +
                "****************************************" + "\n" +
                "*                                      *" + "\n" +
                "*   WELCOME TO THE DEALERSHIP PROGRAM  *" + "\n" +
                "*                                      *" + "\n" +
                "****************************************"
                + Ansi.RESET);
        int mainCommand;
        do {
            System.out.println("\nMain Menu");
            System.out.println("1) View All Vehicles");
            System.out.println("2) Get Vehicle By VIN");
            System.out.println("3) Create Vehicle");
            System.out.println("4) Update Vehicle");
            System.out.println("5) Delete Vehicle");
            System.out.println("6) Sell vehicle");
            System.out.println("7) Lease vehicle");
            System.out.println("8) View All Dealerships");
            System.out.println("9) Get Dealership By ID");
            System.out.println("0) Exit");
            System.out.print("Option: ");
            mainCommand = scanner.nextInt();
            scanner.nextLine();

            switch (mainCommand) {
                case 1:
                    getAllVehicles();
                    break;
                case 2:
                    getVehicleByVin();
                    break;
                case 3:
                    createVehicle();
                    break;
                case 4:
                    updateVehicle();
                    break;
                case 5:
                    deleteVehicle();
                case 6:
                    sellVehicle();
                    break;
                case 7:
                    leaseVehicle();
                    break;
                case 8:
                    getAllDealerships();
                    break;
                case 9:
                    getDealershipById();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        } while (mainCommand != 0);
    }


    private void getDealershipById() {
        System.out.print("Enter Dealership ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Dealership dealership = dealershipDAO.getById(id);
        if (dealership != null) {
            System.out.println(dealership);
        }
    }


    private void getAllVehicles() {
        List<Vehicle> vehicles = vehicleDAO.getAll();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    private void getVehicleByVin() {
        System.out.print("vin: ");
        String vin = scanner.nextLine();
        System.out.println(vehicleDAO.getByVin(vin));
    }

    private void createVehicle() {
        System.out.print("VIN: ");
        String vin = scanner.nextLine();
        System.out.print("year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("make: ");
        String make = scanner.nextLine();
        System.out.print("model: ");
        String model = scanner.nextLine();
        System.out.print("type: ");
        String type = scanner.nextLine();
        System.out.print("color: ");
        String color = scanner.nextLine();
        System.out.print("odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("sold (enter YES or NO): ");
        String sold = scanner.nextLine();

        Vehicle vehicle = new Vehicle(
                vin,
                year,
                make,
                model,
                type,
                color,
                odometer,
                price,
                sold
        );
        vehicleDAO.create(vehicle);
    }

    private void updateVehicle() {
        System.out.print("vin: ");
        String vin = scanner.nextLine();
        System.out.println("Please enter the updated values for each of the following:");
        System.out.print("year: ");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.print("make: ");
        String make = scanner.nextLine();
        System.out.print("model: ");
        String model = scanner.nextLine();
        System.out.print("type: ");
        String type = scanner.nextLine();
        System.out.print("color: ");
        String color = scanner.nextLine();
        System.out.print("odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("sold (enter YES or NO): ");
        String sold = scanner.nextLine();

        Vehicle vehicleToUpdate = new Vehicle(
                vin,
                year,
                make,
                model,
                type,
                color,
                odometer,
                price,
                sold
        );
        vehicleDAO.update(vin, vehicleToUpdate);
    }

    private void deleteVehicle() {
        System.out.print("vin: ");
        String vinToDelete = scanner.nextLine();
        vehicleDAO.delete(vinToDelete);
    }

    private void sellVehicle() {
        System.out.println("\n--- Sell A Vehicle ---");
        System.out.print("Contract date (YYYY-MM-DD): ");
        LocalDate contractDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Customer email: ");
        String customerEmail = scanner.nextLine();
        System.out.print("Total price: ");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Financed (enter YES or NO): ");
        String financed = scanner.nextLine();
        System.out.print("Vehicle VIN: ");
        String vin = scanner.nextLine();

        SalesContract contract = new SalesContract(
                0,
                contractDate,
                customerName,
                customerEmail,
                totalPrice,
                financed,
                vin
        );
        salesContractDAO.create(contract);
        vehicleDAO.markAsSold(vin);
    }

    private void leaseVehicle() {
        System.out.println("\n--- Lease a Vehicle ---");
        System.out.print("Contract date (YYYY-MM-DD): ");
        LocalDate contractDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Customer email: ");
        String customerEmail = scanner.nextLine();
        System.out.print("Monthly payment: ");
        double monthlyPayment = scanner.nextDouble();
        System.out.print("Leasing period (months): ");
        int leasingPeriod = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Start date of lease(YYYY-MM-DD): ");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.print("End date of Lease(YYYY-MM-DD): ");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Vehicle vin: ");
        String vin = scanner.nextLine();

        LeaseContract leaseContract = new LeaseContract(
                0,
                contractDate,
                customerName,
                customerEmail,
                monthlyPayment,
                leasingPeriod,
                startDate,
                endDate,
                vin
        );
        leaseContractDAO.create(leaseContract);
        vehicleDAO.markAsSold(vin);
    }

    private void getAllDealerships() {
        List<Dealership> dealerships = dealershipDAO.getAll();
        for (Dealership dealership : dealerships) {
            System.out.println(dealership);
        }
    }
}
