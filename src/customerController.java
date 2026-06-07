/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dinam
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class customerController {

    private  Inventory inventory;

    // Fetch vehicles based on user criteria
    public List<Object[]> searchVehicles(String model, String fuel) {
        List<Object[]> data = new ArrayList<>();
        try (Connection con = DBConnect.connect()) {
            // Ensure column names match APP.CARS schema exactly
            String sql = "SELECT VEHICLE_ID, MAKE, MODEL, YEAR, LICENSE_PLATE, DAILY_RATE, STATUS, FUEL_TYPE, TYPE FROM PRO.CARS WHERE MODEL = ? AND FUEL_TYPE = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, model);
            pst.setString(2, fuel);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                data.add(new Object[]{
                    rs.getString("VEHICLE_ID"),
                    rs.getString("MAKE"),
                    rs.getString("MODEL"),
                    rs.getInt("YEAR"),
                    rs.getString("LICENSE_PLATE"),
                    rs.getDouble("DAILY_RATE"), 
                    rs.getString("STATUS"),
                    rs.getString("FUEL_TYPE"),
                    rs.getString("TYPE")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public customerController(){};
 public customerController(Inventory inventory) {
        this.inventory = inventory;
    }
 public double calculateCost(String vehicleID, int days) {
        Vehicle v = inventory.getVehicle(vehicleID);

        if (v != null) {
            return v.calculateTotalRental(days);
        }
        return 0;
    }
    // Save reservation matching the RESERVATIONS table schema
   public boolean makeReservation(String customerID, String start, String end, double total, String status) {
    try (Connection con = DBConnect.connect()) {
        String sql = "INSERT INTO PRO.RESERVATIONS (CUSTOMER_ID, START_DATE, END_DATE, TOTAL_COST, STATUS) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, customerID);
        pst.setString(2, start);
        pst.setString(3, end);
        pst.setDouble(4, total);
        pst.setString(5, "Confirmed");
        return pst.executeUpdate() > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
}