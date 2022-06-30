package me.bjornvanwilligen.stormmc.datalayer.app.connector;

import java.sql.*;

public class DatabaseConnector {

    private static DatabaseConnector INSTANCE;

    private Connection connection;
    private String connectionString = "jdbc:mysql://31.187.74.249:3306";
    private String timeZone = "?serverTimezone=Europe/Amsterdam";

    private String schemaName = "SURVIVAL";

    private DatabaseConnector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(connectionString + timeZone, "survival", "bi326288!");

            String createSchemaQuery = "CREATE SCHEMA IF NOT EXISTS " + schemaName + ";";
            try (PreparedStatement statement = this.connection.prepareStatement(createSchemaQuery)) {
                int result = statement.executeUpdate();
                System.out.println("Creating schema: " + schemaName + " || Result: " + result);
                this.connection = DriverManager.getConnection(connectionString + "/" + schemaName + timeZone, "survival", "bi326288!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return this.connection;
    }

    public static DatabaseConnector getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DatabaseConnector();
        }
        return INSTANCE;
    }
}
