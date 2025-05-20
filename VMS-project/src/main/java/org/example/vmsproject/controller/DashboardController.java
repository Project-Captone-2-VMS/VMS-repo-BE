package org.example.vmsproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Vehicle;
import org.example.vmsproject.service.impl.DashboardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/total-drivers")
    public ResponseEntity<Long> getTotalDrivers() {
        return ResponseEntity.ok(dashboardService.getTotalDrivers());
    }

    @GetMapping("/total-vehicles")
    public ResponseEntity<Long> getTotalVehicles() {
        return ResponseEntity.ok(dashboardService.getTotalVehicles());
    }

    @GetMapping("/total-routes")
    public ResponseEntity<Long> getTotalRoutes() {
        return ResponseEntity.ok(dashboardService.getTotalRoutes());
    }

    @GetMapping("/completed-routes")
    public ResponseEntity<Long> getCompletedRoutesCount() {
        return ResponseEntity.ok(dashboardService.getCompletedRoutesCount());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Route>> getAllActiveRoutes() {
        return ResponseEntity.ok(dashboardService.getAllActiveRoutes());
    }

    @GetMapping("/vehicles/inactive/count")
    public ResponseEntity<Long> getInactiveVehiclesCount() {
        return ResponseEntity.ok(dashboardService.getInactiveVehiclesCount());
    }

    @GetMapping("/vehicles/inactive")
    public ResponseEntity<List<Vehicle>> getInactiveVehicles() {
        return ResponseEntity.ok(dashboardService.getInactiveVehicles());
    }

    @GetMapping("/vehicles/active/count")
    public ResponseEntity<Long> getActiveVehiclesCount() {
        return ResponseEntity.ok(dashboardService.getActiveVehiclesCount());
    }

    @GetMapping("/vehicles/active")
    public ResponseEntity<List<Vehicle>> getActiveVehicles() {
        return ResponseEntity.ok(dashboardService.getActiveVehicles());
    }
}