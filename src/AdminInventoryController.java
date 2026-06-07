/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

public class AdminInventoryController  {
    private Inventory inventory;

    
    public List<Vehicle> viewVehicles() {
    return inventory.getAllVehicles();
}

    public void addVehicle(Vehicle vehicle) {
        inventory.addVehicle(vehicle);
    }

    public Inventory getInventory() {
        return inventory;
    }
    
    public List<Vehicle> findVehicle(String model) {
        return inventory.searchVehicle(model);
    }
    public boolean login(String email, String password) {
        return "admin@mail.com".equals(email) && "123".equals(password);
    }


}