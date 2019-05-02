package com.github.derpynewbie.databasetest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    private static Connection conn;
    private static DatabaseHandler handler;

    public static void closeConnection() {
        try {
            if (!conn.isValid(1000) && conn.isClosed()) {
                throw new IllegalStateException("connection is already closed");
            }
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void openConnection() {
        System.out.println("Opening connection...");
        try {
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        } catch (SQLException ex) {
            System.out.println("Cannot connect to H2 Database.\n");
            ex.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (conn.isClosed()) {
            openConnection();
        }
        return conn;
    }

    public DatabaseHandler getInstance() {
        if (handler == null) {
            return new DatabaseHandler();
        } else {
            return DatabaseHandler.handler;
        }
    }

    private DatabaseHandler() {
        DatabaseHandler.handler = this;
        openConnection();
    }

}
