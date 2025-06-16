package com.ps;

import com.ps.data.DealershipDAO;
import com.ps.data.LeaseContractDAO;
import com.ps.data.SalesContractDAO;
import com.ps.data.VehicleDAO;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Username and/or password is missing");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/dealership");
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        VehicleDAO vehicleDAO = new VehicleDAO(dataSource);
        SalesContractDAO salesDAO = new SalesContractDAO(dataSource);
        LeaseContractDAO leaseDAO = new LeaseContractDAO(dataSource);
        DealershipDAO dealershipDAO = new DealershipDAO(dataSource);

        UserInterface userInterface = new UserInterface(
                vehicleDAO,
                salesDAO,
                leaseDAO,
                dealershipDAO
        );

        userInterface.display();
    }
}
