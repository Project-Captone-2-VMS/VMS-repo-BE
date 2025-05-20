package org.example.vmsproject.repository;

import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query("SELECT v FROM Vehicle v WHERE  v.status = false ")
    List<Vehicle> findAllVehicleNoActive();

    @Query("select count(*) as total_vehicles From Vehicle ")
    int findTotalVehicles();

    boolean existsByLicensePlate(String licensePlate);

    @Query("SELECT v FROM Vehicle v WHERE v.vehicleId NOT IN (SELECT r.vehicle.vehicleId FROM Route r)")
    List<Vehicle> findVehiclesWithNoRoutes();

    @Query("SELECT DISTINCT v FROM Vehicle v JOIN Route r ON v.vehicleId = r.vehicle.vehicleId")
    List<Vehicle> findVehiclesWithRoutes();
}
