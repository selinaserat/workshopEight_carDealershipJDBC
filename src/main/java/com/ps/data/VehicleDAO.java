package com.ps.data;


import com.ps.models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {

    private DataSource dataSource;

    public VehicleDAO(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<Vehicle> getAll(){
        List<Vehicle> vehicles = new ArrayList<>();

        String query = "SELECT * FROM vehicles;";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                ) {
            if (resultSet.next()) {
                do {
                    Vehicle vehicle = vehicleParser(resultSet);
                    vehicles.add(vehicle);
                } while (resultSet.next());
            } else {
                System.out.println("no vehicles found");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return vehicles;

    }

    public Vehicle getByVin(String vin){

        String query = "SELECT * FROM vehicles WHERE vin = ?;";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, vin);

            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                if(resultSet.next()){
                    return vehicleParser(resultSet);
                } else {
                    System.out.println("No vehicle found");
                }
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public void create(Vehicle vehicle){
        String query = "INSERT INTO " +
                "vehicles(vin, vehicle_year, make, model, vehicle_type, color, odometer, price, sold) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ){
            preparedStatement.setString(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getType());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setInt(7, vehicle.getOdometer());
            preparedStatement.setDouble(8, vehicle.getPrice());
            preparedStatement.setString(9, vehicle.getSold());

            int rows = preparedStatement.executeUpdate();

            if(rows ==1){
                System.out.println("Vehicle succesfully created");
            } else {
                System.out.println("Vehicle creation failed");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void update(String vin, Vehicle vehicle){
        String query = "UPDATE vehicles " +
                "SET vehicle_year = ?, make = ?, model = ?, vehicle_type = ?, color = ?, odometer = ?, price = ?, sold = ?" +
                "WHERE vin = ?;";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, vehicle.getYear());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getType());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setInt(6, vehicle.getOdometer());
            preparedStatement.setDouble(7, vehicle.getPrice());
            preparedStatement.setString(8, vehicle.getSold());
            preparedStatement.setString(9, vehicle.getVin());


            int rows = preparedStatement.executeUpdate();

            if(rows ==1){
                System.out.println("Vehicle succesfully updated");
            } else {
                System.out.println("Vehicle update failed");
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    public void delete(String vin){
        String query = "DELETE FROM vehicles WHERE vin = ?;";

        try(
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);

                ){
            preparedStatement.setString(1, vin);

            int rows = preparedStatement.executeUpdate();

            if(rows == 1){
                System.out.println("Vehicle successfully deleted");
            }else{
                System.out.println("Vehicle deletion failed");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    public void markAsSold(String vin) {
        String query = "UPDATE vehicles SET sold = 'YES' WHERE vin = ?;";
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setString(1, vin);
            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Vehicle successfully marked as sold.");
            } else {
                System.out.println("Failed to mark vehicle as sold.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private Vehicle vehicleParser(ResultSet resultSet) throws SQLException{

        String vin = resultSet.getString("vin");
        int year = resultSet.getInt("vehicle_year");
        String make = resultSet.getString("make");
        String model = resultSet.getString("model");
         String vehicleType = resultSet.getString("vehicle_type");
         String color = resultSet.getString("color");
         int odometer = resultSet.getInt("odometer");
         double price = resultSet.getDouble("price");
         String sold= resultSet.getString("sold");

         return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, sold);
    }


}


