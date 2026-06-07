/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    public static Connection connect() {
    try {
        // New URL with the new name
        String url = "jdbc:derby://localhost:1527/car";
        
        // New Username and Password
        return DriverManager.getConnection(url, "pro", "1234"); 
        
    } catch (SQLException e) {
        System.out.println("Connection Failed: " + e.getMessage());
        return null;
    }
}
public static void main(String[] args) {
    if (connect() != null) {
        System.out.println("Success! The URL is correct.");
    } else {
        System.out.println("Failed! Check if the Derby server is started in the Services tab.");
    }
}
}