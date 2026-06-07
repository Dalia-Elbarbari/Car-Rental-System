/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author LenOvo
 */
public class Vehicle {

    private String vehicleID;
    private String make;
    private String model;
    private int vehicleyear;
    private String licensePlate;
    private double dailyRate;
    private VehicleStatus status;
    private FuelType fuelType;
    private String type;
    private String date;

    public Vehicle() {}

    public Vehicle(String vehicleID, String make, String model, int year,
                   String licensePlate, double dailyRate,
                   VehicleStatus status, FuelType fuelType) {

        setVehicleID(vehicleID);
        setDailyRate(dailyRate);
        setYear(year); 

        this.make = make;
        this.model = model;
        this.licensePlate = licensePlate;
        this.status = status;
        this.fuelType = fuelType;
    }

    public boolean isAvailable() {
        return this.status == VehicleStatus.Available;
    }

    public void updateStatus(VehicleStatus newStatus) {
        this.status = newStatus;
    }

    public boolean needsMaintenance() {
        return this.status == VehicleStatus.Maintenance;
    }

    public String getVehicleDetails() {
        return "ID: " + vehicleID + ", " + vehicleyear + " " + make + " " + model;
    }

    // Getters & Setters
    public String getVehicleID() { return vehicleID; }

    public void setVehicleID(String vehicleID) {
        if (vehicleID == null || vehicleID.trim().isEmpty()) {
            throw new IllegalArgumentException("Vehicle ID cannot be empty");
        }
        this.vehicleID = vehicleID;
    }

    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return vehicleyear; }

    public void setYear(int year) {
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        if (year < 1900 || year > currentYear + 1) {
            throw new IllegalArgumentException("Invalid year");
        }
        this.vehicleyear = year;
    }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public double getDailyRate() { return dailyRate; }

    public void setDailyRate(double dailyRate) {
        if (dailyRate < 0) {
            throw new IllegalArgumentException("Rate cannot be negative");
        }
        this.dailyRate = dailyRate;
    }
public double calculateTotalRental(int days) {
        // Simple calculation: rate * duration
        double total = this.dailyRate * days;
        
        // You can add logic for discounts here if needed
        // e.g., if (days > 7) total *= 0.90; // 10% discount
        
        return total;
    }
    public VehicleStatus getStatus() { return status; }
    public void setStatus(VehicleStatus status) { this.status = status; }

    public FuelType getFuelType() { return fuelType; }
    public void setFuelType(FuelType fuelType) { this.fuelType = fuelType; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}