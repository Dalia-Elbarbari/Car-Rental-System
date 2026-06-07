
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author LamisEsmat
 */
public class User {
    private int userID;
    private String userName;
    private String email;
    private String password;
    private boolean loginStatus;
 
    //  Constructors
 
    public User() {}
    public User(int userID, String userName, String email,
                String password, boolean loginStatus) {
        this.userID      = userID;
        this.userName    = userName;
        this.email       = email;
        this.password    = password;
        this.loginStatus = loginStatus;
    }
 
    // Getters & Setters

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isLoginStatus() {
        return loginStatus;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLoginStatus(boolean loginStatus) {
        this.loginStatus = loginStatus;
    }
 
    
 
    // ── Business Methods ──────────────────────────────────────────────────────
 
    /**
     * Validates the supplied password against the stored one.
     *
     * @param password plain-text password attempt
     * @return true if it matches
     */
    public boolean login(String password) {
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password must not be empty.");
        }
        return this.password.equals(password);
    }
 
    public void logout() {
        this.loginStatus = false;
    }
 
    @Override
    public String toString() {
        return "User{id=" + userID + ", userName='" + userName + "', email='" + email + "'}";
    }
    
}