package com.ps.data;

import com.ps.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDAO {

    private DataSource dataSource;

    public SalesContractDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<SalesContract> getAll() {
        List<SalesContract> salesContracts = new ArrayList<>();
        String query = "SELECT * FROM sales_contracts;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                SalesContract contract = salesContractParser(resultSet);
                salesContracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salesContracts;
    }

    public SalesContract getById(int contractId) {
        String query = "SELECT * FROM sales_contracts WHERE contract_id = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, contractId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return salesContractParser(resultSet);
                } else {
                    System.out.println("No contract found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void create(SalesContract contract) {
        String query = "INSERT INTO sales_contracts " +
                "(contract_date, customer_name, customer_email, total_price, financed, VIN) " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setDate(1, Date.valueOf(contract.getContractDate()));
            preparedStatement.setString(2, contract.getCustomerName());
            preparedStatement.setString(3, contract.getCustomerEmail());
            preparedStatement.setDouble(4, contract.getTotalPrice());
            preparedStatement.setString(5, contract.getFinanced());
            preparedStatement.setString(6, contract.getVin());

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Sales contract successfully created.");
            } else {
                System.out.println("Sales contract creation failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int contractId, SalesContract contract) {
        String query = "UPDATE sales_contracts " +
                "SET contract_date = ?, customer_name = ?, customer_email = ?, total_price = ?, financed = ?, VIN = ? " +
                "WHERE contract_id = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setDate(1, Date.valueOf(contract.getContractDate()));
            preparedStatement.setString(2, contract.getCustomerName());
            preparedStatement.setString(3, contract.getCustomerEmail());
            preparedStatement.setDouble(4, contract.getTotalPrice());
            preparedStatement.setString(5, contract.getFinanced());
            preparedStatement.setString(6, contract.getVin());
            preparedStatement.setInt(7, contractId);

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("sales contract successfully updated.");
            } else {
                System.out.println("sales contract update failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int contractId) {
        String query = "DELETE FROM sales_contracts WHERE contract_id = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, contractId);

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Sales contract successfully deleted.");
            } else {
                System.out.println("sales contract deletion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private SalesContract salesContractParser(ResultSet resultSet) throws SQLException {
        int contractId = resultSet.getInt("contract_id");
        java.time.LocalDate contractDate = resultSet.getDate("contract_date").toLocalDate();
        String customerName = resultSet.getString("customer_name");
        String customerEmail = resultSet.getString("customer_email");
        double totalPrice = resultSet.getDouble("total_price");
        String financed = resultSet.getString("financed");
        String vin = resultSet.getString("VIN");

        return new SalesContract(contractId, contractDate, customerName, customerEmail, totalPrice, financed, vin);
    }
}
