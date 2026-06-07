/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author dinam
 */
public class Reservation {
    private String reservationId;
    private String startDate;
    private String endDate;
    private double totalCost;
    private String status; 
    private String createdAt;
    private String updatedAt;
    
    public Reservation(String id, String start, String end, double cost, String status, String createdAt, String updatedAt) {
        this.reservationId = id;
        this.startDate = start;
        this.endDate = end;
        this.totalCost = cost;
        this.status = status;
        this.createdAt=createdAt;
        this.updatedAt=updatedAt;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }
public Reservation(){
    
}
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

  
    public String getReservationId() { return reservationId; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }
}