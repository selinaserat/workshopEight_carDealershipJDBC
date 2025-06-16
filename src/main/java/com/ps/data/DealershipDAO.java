package com.ps.data;

import com.ps.models.Dealership;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAO {

    private DataSource dataSource;

    public DealershipDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dealership> getAll() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM dealerships;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Dealership dealership = dealershipParser(resultSet);
                dealerships.add(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dealerships;
    }

    public Dealership getById(int dealershipId) {
        String query = "SELECT * FROM dealerships WHERE dealership_id = ?;";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, dealershipId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return dealershipParser(resultSet);
                } else {
                    System.out.println("No dealership found");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    private Dealership dealershipParser(ResultSet resultSet) throws SQLException {
        int dealershipId = resultSet.getInt("dealership_id");
        String dealershipName = resultSet.getString("dealership_name");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");

        return new Dealership(dealershipId, dealershipName, address, phone);
    }
}
