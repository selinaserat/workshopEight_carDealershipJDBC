package com.ps.data;

import com.ps.models.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDAO {

    private DataSource dataSource;

    public LeaseContractDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<LeaseContract> getAll() {
        List<LeaseContract> contracts = new ArrayList<>();
        String query = "SELECT * FROM lease_contracts;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery()
        ) {
            while (resultSet.next()) {
                LeaseContract contract = leaseContractParser(resultSet);
                contracts.add(contract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contracts;
    }

    public LeaseContract getById(int contractId) {
        String query = "SELECT * FROM lease_contracts WHERE contract_id = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, contractId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return leaseContractParser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void create(LeaseContract contract) {
        String query = "INSERT INTO lease_contracts " +
                "(contract_date, customer_name, customer_email, monthly_payment, leasing_period, start_date, end_date, VIN) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setDate(1, Date.valueOf(contract.getContractDate()));
            preparedStatement.setString(2, contract.getCustomerName());
            preparedStatement.setString(3, contract.getCustomerEmail());
            preparedStatement.setDouble(4, contract.getMonthlyPayment());
            preparedStatement.setInt(5, contract.getLeasingPeriod());
            preparedStatement.setDate(6, Date.valueOf(contract.getStartDate()));
            preparedStatement.setDate(7, Date.valueOf(contract.getEndDate()));
            preparedStatement.setString(8, contract.getVin());

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Lease contract successfully created.");
            } else {
                System.out.println("Lease contract creation failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(int contractId, LeaseContract contract) {
        String query = "UPDATE lease_contracts " +
                "SET contract_date = ?, customer_name = ?, customer_email = ?, monthly_payment = ?, leasing_period = ?, start_date = ?, end_date = ?, VIN = ? " +
                "WHERE contract_id = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setDate(1, Date.valueOf(contract.getContractDate()));
            preparedStatement.setString(2, contract.getCustomerName());
            preparedStatement.setString(3, contract.getCustomerEmail());
            preparedStatement.setDouble(4, contract.getMonthlyPayment());
            preparedStatement.setInt(5, contract.getLeasingPeriod());
            preparedStatement.setDate(6, Date.valueOf(contract.getStartDate()));
            preparedStatement.setDate(7, Date.valueOf(contract.getEndDate()));
            preparedStatement.setString(8, contract.getVin());
            preparedStatement.setInt(9, contractId);

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Lease contract successfully updated.");
            } else {
                System.out.println("Lease contract update failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int contractId) {
        String query = "DELETE FROM lease_contracts WHERE contract_id = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, contractId);

            int rows = preparedStatement.executeUpdate();

            if (rows == 1) {
                System.out.println("Lease contract successfully deleted.");
            } else {
                System.out.println("Lease contract deletion failed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private LeaseContract leaseContractParser(ResultSet resultSet) throws SQLException {
        int contractId = resultSet.getInt("contract_id");
        java.time.LocalDate contractDate = resultSet.getDate("contract_date").toLocalDate();
        String customerName = resultSet.getString("customer_name");
        String customerEmail = resultSet.getString("customer_email");
        double monthlyPayment = resultSet.getDouble("monthly_payment");
        int leasingPeriod = resultSet.getInt("leasing_period");
        java.time.LocalDate startDate = resultSet.getDate("start_date").toLocalDate();
        java.time.LocalDate endDate = resultSet.getDate("end_date").toLocalDate();
        String vin = resultSet.getString("VIN");

        return new LeaseContract(contractId, contractDate, customerName, customerEmail, monthlyPayment, leasingPeriod, startDate, endDate, vin);
    }
}
