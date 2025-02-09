package org.management;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://dpg-cuk794tds78s739l4teg-a.oregon-postgres.render.com:5432/computer_inventory_management?sslmode=require";
    private static final String USER = "computer_inventory_management_user";
    private static final String PASSWORD = "OFVbEMzykjpD3gbzOCdkyKEcTKDNEj4s";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("✅ Connected to PostgreSQL on Render successfully!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
        }
    }
}

