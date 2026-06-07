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
public class Admin extends User {
    
    
   private int    adminID;
    private String department;
    private String adminLevel;
    private List<String> permissions;   
 
    // Constructor
 
    public Admin(int userID, String userName, String email,String password, boolean loginStatus,int adminID, String department,
                 String adminLevel, List<String> permissions)
    {
        this.adminID     = adminID;
        this.department  = department;
        this.adminLevel  = adminLevel;
        this.permissions = permissions != null ? new ArrayList<>(permissions) : new ArrayList<>();
    }
 
    // Getters & Setters 

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }
 
    
 
    //  Business Methods
    public boolean validateCredentials(String password) {
        if (password == null || password.isBlank())
            throw new IllegalArgumentException("Password must not be empty.");
        return login(password);
    }
 
    
    public boolean hasPermission(String permission) {
        if (permission == null || permission.isBlank())
            throw 
                    new IllegalArgumentException("Permission key must not be empty.");
        return permissions.contains(permission.toUpperCase());
    }
 
    
    public String getAdminDetails() {
        return String.format("Admin[id=%d, level=%s, dept=%s, permissions=%s]",
                adminID, adminLevel, department, permissions);
    }
 
    @Override
    public String toString() {
        return "Admin{adminID=" + adminID + ", department='" + department
             + "', adminLevel='" + adminLevel + "', email='" + getEmail() + "'}";
    } 
    
}