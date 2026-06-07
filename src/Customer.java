/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author LamisEsmat
 */

public class Customer extends User {
 
    private String customerID;   // business-level ID (e.g. "CUST-001")
    private String name;
    private String address;
    private String phoneNumber;
    private final List<String> paymentHistory;   
    private final List<String> reservations;    
 
    //  Constructors 
 
    public Customer() {
        super();
        paymentHistory = new ArrayList<>();
        reservations   = new ArrayList<>();
    }
 
    public Customer(int userID, String userName, String email,
                    String password, boolean loginStatus,
                    String customerID, String name,
                    String address, String phoneNumber) {
        super(userID, userName, email, password, loginStatus);
        this.customerID    = customerID;
        this.name          = name;
        this.address       = address;
        this.phoneNumber   = phoneNumber;
        this.paymentHistory = new ArrayList<>();
        this.reservations   = new ArrayList<>();
    }
 
    // ── Getters & Setters

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getPaymentHistory() {
        return paymentHistory;
    }

    public List<String> getReservations() {
        return reservations;
    }
 
    
    public void updateProfile(String address, String phoneNumber) {
        if (address != null && !address.isBlank())
            this.address = address;
        if (phoneNumber != null && !phoneNumber.isBlank())
            this.phoneNumber = phoneNumber;
    }
 
    /** Returns a snapshot of payment history IDs. */
    public List<String> getPaymentHistoryList() {
        return getPaymentHistory();
    }
 
    /** Adds a reservation ID to the in-memory list. */
    public void addReservation(String reservationID) {
        if (reservationID == null || reservationID.isBlank())
            throw new IllegalArgumentException("Reservation ID must not be empty.");
        reservations.add(reservationID);
    }
 
    /** Returns the active (non-cancelled) reservation IDs held in memory. */
    public List<String> getActiveReservations() {
        // Real filtering is done via ReservationDAO; this returns local snapshot
        return getReservations();
    }
 
    /** Removes a reservation ID from the in-memory list. */
    public boolean cancelReservation(String reservationID) {
        return reservations.remove(reservationID);
    }
 
    @Override
    public String toString() {
        return "Customer{customerID='" + customerID + "', name='" + name
             + "', email='" + getEmail() + "'}";
    }
}