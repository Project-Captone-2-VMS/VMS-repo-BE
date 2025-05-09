package org.example.vmsproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle_expense")
public class VehicleExpense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long expenseId;
    private String description;
    private Long maintenanceCost;
    private Double fuelCost;
    private LocalDate date;
    private String category;
    private final double oilCost = 20000;
    private double fuelConsumption;


    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;
    @OneToOne
    @JoinColumn(name = "route_id", nullable = false)
    private Route route;

    public Double getFuelCost() {
        if (this.vehicle.getCapacity() >= 1 && this.vehicle.getCapacity() <= 5) {
            this.fuelCost = oilCost * (this.route.getTotalDistance() /10);
        }
        else if (this.vehicle.getCapacity() > 5 && this.vehicle.getCapacity() <= 10) {
            this.fuelCost = oilCost * (this.route.getTotalDistance() / 15);
        } else if (this.vehicle.getCapacity() > 10 && this.vehicle.getCapacity() <= 15) {
            this.fuelCost = oilCost * (this.route.getTotalDistance() / 20);
        } else if (this.vehicle.getCapacity() > 15 && this.vehicle.getCapacity() <= 20) {
            this.fuelCost = oilCost * (this.route.getTotalDistance() / 30);
        }
        return fuelCost;
    }

//    public Long getMaintenanceCost() {
//        return maintenanceCost;
//    }

    //    public static void main(String[] args) {
//        Vehicle vehicle = new Vehicle();
//        vehicle.setCapacity(11);
//        Route route = new Route();
//        route.setTotalDistance(100);
//        VehicleExpense vehicleExpense = new VehicleExpense();
//        vehicleExpense.setVehicle(vehicle);
//        vehicleExpense.setRoute(route);
//        System.out.println("Fuel Cost: " + vehicleExpense.getFuelCost());
//    }


}

