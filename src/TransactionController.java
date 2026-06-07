/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author daliaelbarbary
 */
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TransactionController {

    private int verificationAttempts = 0;

    // Initiates the flow: TransactionController -> Reservation (getTotalCost)
    public double processTransaction(Reservation res, Payment pay, String selectedCurrency) {
        double finalAmount = 0.0;
        try {
            if (res != null && pay != null) {
                // Get the original EGP cost
                double originalCost = res.getTotalCost();

                // Perform conversion
                pay.convertCurrency(selectedCurrency, originalCost);

                // Get the new amount from payment object
                finalAmount = pay.getAmount();
            }
        } catch (Exception e) {
            System.err.println("Error in processing amount: " + e.getMessage());
        }
        return finalAmount;
    }

    // Validates the initial response from the payment gateway
    public String validateResponse(String cardNumber, String cvv, String expiry) {
        try {
            // Check for empty fields or default placeholder text
            if (cardNumber.trim().isEmpty() || cvv.trim().isEmpty() || expiry.trim().isEmpty()
                    || cardNumber.equals("Card Number") || expiry.equals("MM/YY") || cvv.equals("CVV")) {
                return "All fields are required. Please fill in all card details.";
            }

            // Check if card number contains only digits and has a valid minimum length
            if (!cardNumber.matches("\\d+") || cardNumber.length() < 6) {
                return "Invalid Card Number: Must be numeric and at least 6 digits.";
            }

            // Validate CVV format (assuming 3 digits)
            if (!cvv.matches("\\d{3}")) {
                return "Invalid CVV: Must be exactly 3 digits.";
            }

            // Expiry Date Validation using Java Time API
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
                YearMonth expiryDate = YearMonth.parse(expiry, formatter);

                // Compare with current system date
                if (expiryDate.isBefore(YearMonth.now())) {
                    return "Transaction Declined: This card has expired.";
                }
            } catch (DateTimeParseException e) {
                // Handle cases like 13/99 or incorrect formatting
                return "Invalid Expiry Date format. Please use MM/YY.";
            }

            // If all validation logic passes
            return "SUCCESS";

        } catch (Exception e) {
            // Catch any unexpected runtime exceptions
            return "System Error: " + e.getMessage();
        }
    }

    public String requestVerificationCode(Payment pay) {

        // Generate random code
        int code = (int) (Math.random() * 9000) + 1000;
        String generatedCode = String.valueOf(code);

        // Set it in the Payment object (Entity)
        pay.setAuthCode(generatedCode);

        // Return it to GUI
        return generatedCode;
    }

    // Handles the loop (1,3) logic for the verification code
    
    
    public String verifyCode(String userInput, String actualCode, Reservation res, Payment pay) {
        // Case 1: Correct Code
        if (userInput.trim().equals(actualCode)) {
            res.setStatus("Confirmed");
            pay.confirmTransaction("Confirmed"); // Insert with 'Confirmed'
            return "SUCCESS";
        } // Case 2: Wrong Code
        else {
            verificationAttempts++;
            if (verificationAttempts >= 3) {
                res.setStatus("Cancelled");
                // --- ADD THIS LINE ---
                pay.confirmTransaction("Cancelled"); // Now it inserts 'Cancelled' into the DB!
                return "CANCELLED";
            }
            return "WRONG";
        }
    }
     
    public String printReceipt(Payment pay, Reservation res) {
        return """
               ==========================================
                                       PAYMENT RECEIPT           
               ==========================================
                Reservation ID : %d
                Total Amount   : %.2f %s
                Date           : %s
                Status         : %s
               ==========================================
               """.formatted(res.getReservationId(), pay.getAmount(), pay.getCurrencyCode(), pay.getPaymentDate(), pay.getPaymentStatus());
    }

    //Returns current verification attempts to the GUI.
    public int getVerificationAttempts() {
        return verificationAttempts;
    }
}
