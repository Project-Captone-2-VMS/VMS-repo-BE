package org.example.vmsproject.repository;

import org.example.vmsproject.dto.DriverRouteStatsDTO;
import org.example.vmsproject.dto.VehicleRouteStatsDTO;
import org.example.vmsproject.entity.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query("SELECT r FROM Route r WHERE r.status = false")
    List<Route> getAllRoutesByStatus();

    @Query("SELECT r FROM Route r WHERE r.status = true")
    List<Route> getAllRoutesDone();

    @Query("SELECT r " +
            "FROM Route r " +
            "JOIN r.driver d " +
            "JOIN User u ON u.email = d.email " +
            "WHERE u.username = :username " +
            "AND r.status = false")
    List<Route> findRoutesByUsername(@Param("username") String username);

    @Query("SELECT r " +
            "FROM Route r " +
            "JOIN r.driver d " +
            "JOIN User u ON u.email = d.email " +
            "WHERE u.username = :username ")
    List<Route> listAllRouteByUsername(@Param("username") String username);

    @Query("SELECT r " +
            "FROM Route r " +
            "JOIN r.driver d " +
            "JOIN User u ON u.email = d.email " +
            "WHERE u.username = :username " +
            "AND r.status = true")
    List<Route> findRoutesByUsernameDone(@Param("username") String username);
    Page<Route> findByStatus(boolean status, Pageable pageable);
    long countByStatus(boolean status);

    @Query("SELECT MIN(r.routeDate) FROM Route r")
    Date findMinRouteDate();

    @Query("SELECT MAX(r.routeDate) FROM Route r")
    Date findMaxRouteDate();

    List<Route> findAllByRouteDateBetween(Date startDate, Date endDate);


    @Query("SELECT d.driverId AS driverId, d.lastName AS driverName, COUNT(r) AS routeCount " +
            "FROM Route r JOIN r.driver d GROUP BY d.driverId, d.lastName")
    List<DriverRouteStatsDTO> countRoutesByDriver();

    @Query("SELECT v.vehicleId AS vehicleId, v.licensePlate AS vehicleName, COUNT(r) AS routeCount " +
            "FROM Route r JOIN r.vehicle v GROUP BY v.vehicleId, v.licensePlate")
    List<VehicleRouteStatsDTO> countRoutesByVehicle();

    @Query("SELECT r.vehicle.vehicleId, COUNT(r) FROM Route r GROUP BY r.vehicle.vehicleId")
    Map<String, Long> getVehicleRouteCounts();

    @Query("SELECT r.driver.driverId, COUNT(r) FROM Route r GROUP BY r.driver.driverId")
    Map<String, Long> getDriverRouteCounts();

}
