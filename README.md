# Car Dealership Database System 

This is a Java console-based application that simulates a car dealership management system. The application allows users to manage vehicle inventory, sell or lease vehicles, and view dealership information. It uses a MySQL database to store all data.

## Features

- View all vehicles in the database
- Search for a vehicle by VIN
- Add, update, or delete vehicle records
- Sell a vehicle and record a sales contract
- Lease a vehicle and record a lease contract
- View all dealerships
- Look up a dealership by its ID

## Interesting Code

The most interesting part of this project to me is the DAO files. Each table in the database has its own DAO class, and that's where all the database logic happens. The DAO handles getting data, inserting new records, updating, and deleting. I like it because the rest of the code just calls the DAO methods, and you don’t have to write SQL everywhere. It keeps everything organized and easy to work with.

## Screenshots
### MAIN MENU
![Screenshot 2025-06-15 211928.png](Screenshots/Screenshot%202025-06-15%20211928.png)

### ERROR HANDLING
![Screenshot 2025-06-15 211954.png](Screenshots/Screenshot%202025-06-15%20211954.png)

### VIEWING ALL DEALERSHIPS
![Screenshot 2025-06-15 212025.png](Screenshots/Screenshot%202025-06-15%20212025.png)