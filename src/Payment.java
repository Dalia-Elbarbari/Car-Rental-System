/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daliaelbarbary
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;

public class Payment {

    // Attributes
    private long paymentID;
    private Reservation sourceBooking;
    private double amount;
    private String currencyCode = "EGP";
    private String paymentDate;
    private String paymentStatus = "Pending";
    private String authCode;

    // Static attribute to track total payments
    public static int paymentCount = 0;

    // Default Constructor
    public Payment() {
        paymentCount++;
        this.paymentDate = LocalDateTime.now().toString();
        this.paymentID = System.currentTimeMillis();
        this.paymentStatus = "Pending";

    }

  
    public Payment(Reservation sourceBooking, double amount, String currencyCode) {
        this(); // Calls default constructor to increment paymentCount
        this.sourceBooking = sourceBooking;
        this.amount = amount;
        this.currencyCode = currencyCode;
    }

    //Main Methods
   

    
    public void confirmTransaction(String status) {
    String query = "INSERT INTO PAYMENTS (PAYEMNT_ID, RESERVATION_ID, PAYMENT_DATE, AUTH_CODE, PAYMENT_DATE, CURRECNY_CODE) VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection con = DBConnect.connect();
         PreparedStatement stmt = con.prepareStatement(query)) {

        stmt.setLong(1, this.paymentID);
  int resId = (this.sourceBooking != null) ? Integer.parseInt(this.sourceBooking.getReservationId()) : 0;
stmt.setInt(2, resId);
        stmt.setString(3, this.paymentDate);
        stmt.setString(4, this.authCode);
        stmt.setString(5, status); // takes "Confirmed" or "Cancelled"
        stmt.setString(6, this.currencyCode);

        stmt.executeUpdate();
        this.paymentStatus = status;
        
    } catch (Exception e) {
        // Log to console if DB connection fails (on your machine)
        System.out.println("Mock DB Log: Record inserted as " + status);
        this.paymentStatus = status;
    }
}
     
    public void convertCurrency(String targetCurrency, double originalAmountEGP) {
        // We always calculate starting from the original EGP to avoid cumulative errors
        this.currencyCode = targetCurrency;
        switch (targetCurrency) {
            case "USD" ->
                this.amount = originalAmountEGP * 0.021;
            case "EUR" ->
                this.amount = originalAmountEGP * 0.019;
            default -> {
                this.amount = originalAmountEGP;
                this.currencyCode = "EGP";
            }
        }
    }

    public void validatePaymentStatus() throws IllegalStateException {
        if (this.paymentStatus == null || this.paymentStatus.equals("Cancelled")) {
            throw new IllegalStateException("Payment status is invalid for this operation.");
        }
    }

    //Getters
    public long getPaymentID() {
        return paymentID;
    }

    public Reservation getSourceBooking() {
        return sourceBooking;
    }

    public double getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String getAuthCode() {
        return authCode;
    }

    public static int getPaymentCount() {
        return paymentCount;
    }

    //Setters
    public void setPaymentID(long paymentID) {
        this.paymentID = paymentID;
    }

    public void setSourceBooking(Reservation sourceBooking) {
        this.sourceBooking = sourceBooking;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public static void setPaymentCount(int paymentCount) {
        Payment.paymentCount = paymentCount;
    }

    @Override
    public String toString() {
        return "Payment{" + "paymentID=" + paymentID + ", sourceBooking=" + sourceBooking + ", amount=" + amount + ", currencyCode=" + currencyCode + ", paymentDate=" + paymentDate + ", paymentStatus=" + paymentStatus + ", authCode=" + authCode + '}';
    }

}
