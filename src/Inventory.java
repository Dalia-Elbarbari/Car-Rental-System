
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Vehicle> vehiclesList;
    private int capacity;
public Inventory(){};
    // Constructor
    public Inventory(int capacity) {
        if (capacity <= 0) {
            this.capacity = 10; 
            System.err.println("Invalid capacity. Set to default: 10");
        } else {
            this.capacity = capacity;
        }
        this.vehiclesList = new ArrayList<>();
    }

    // 1. Remove Vehicle
    public boolean removeVehicle(String vehicleId) {
        try {
            if (vehicleId == null || vehicleId.isEmpty()) {
                return false;
            }
            return vehiclesList.removeIf(v -> v.getVehicleID().equals(vehicleId));
        } catch (Exception e) {
            System.err.println("Error during removal: " + e.getMessage());
            return false;
        }
    }

    // 2. Get Specific Vehicle
    public Vehicle getVehicle(String id) {
        if (id == null) return null;
        
        for (Vehicle v : vehiclesList) {
            if (v.getVehicleID().equals(id)) {
                return v;
            }
        }
        System.err.println("Vehicle with ID " + id + " not found.");
        return null; 
    }

    // 3. Get All Vehicles
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehiclesList);
    }

    // 4. Get Available Vehicles
    public List<Vehicle> getAvailableVehicles() {
        List<Vehicle> available = new ArrayList<>();
        for (Vehicle v : vehiclesList) {
            if (v.isAvailable()) {
                available.add(v);
            }
        }
        return available;
    }

    // 5. Search by Model
    public List<Vehicle> searchVehicle(String model) {
        List<Vehicle> results = new ArrayList<>();
        if (model == null) return results;

        for (Vehicle v : vehiclesList) {
            if (v.getModel().equalsIgnoreCase(model)) {
                results.add(v);
            }
        }
        return results;
    }

    // 6. Get by Type
    public List<Vehicle> getVehiclesByType(String type) {
        List<Vehicle> results = new ArrayList<>();
        if (type == null) return results;

        for (Vehicle v : vehiclesList) {
            if (v.getType().equalsIgnoreCase(type)) {
                results.add(v);
            }
        }
        return results;
    }

    // 7. Add Vehicle
    public void addVehicle(Vehicle vehicle) {
        try {
            if (vehicle == null) {
                System.err.println("Cannot add null vehicle.");
                return;
            }
            if (vehiclesList.size() >= capacity) {
                System.err.println("Inventory is full! Capacity reached.");
                return;
            }
            vehiclesList.add(vehicle);
        } catch (Exception e) {
            System.err.println("Failed to add vehicle: " + e.getMessage());
        }
    }

    // 8. Update Vehicle
    public void updateVehicle(Vehicle vehicle) {
        try {
            if (vehicle == null) return;

            for (int i = 0; i < vehiclesList.size(); i++) {
                if (vehiclesList.get(i).getVehicleID().equals(vehicle.getVehicleID())) {
                    vehiclesList.set(i, vehicle);
                    System.out.println("Vehicle updated successfully.");
                    return;
                }
            }
            System.err.println("Update failed: Vehicle not found.");
        } catch (Exception e) {
            System.err.println("Error during update: " + e.getMessage());
        }
    }

    // 9. Check Availability
    public boolean isVehicleAvailable(String vehicleID) {
        Vehicle v = getVehicle(vehicleID);
        return v != null && v.isAvailable();
    }

    // Setters
    public void setCapacity(int capacity) {
        if (capacity >= vehiclesList.size()) {
            this.capacity = capacity;
        }
    }

    public void setVehiclesList(List<Vehicle> vehiclesList) {
        this.vehiclesList = (vehiclesList != null) ? vehiclesList : new ArrayList<>();
    }
}