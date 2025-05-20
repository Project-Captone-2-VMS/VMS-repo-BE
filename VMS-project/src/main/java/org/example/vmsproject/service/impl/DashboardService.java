package org.example.vmsproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Vehicle;
import org.example.vmsproject.repository.DriverRepository;
import org.example.vmsproject.repository.RouteRepository;
import org.example.vmsproject.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final RouteRepository routeRepository;

    public long getTotalDrivers() {
        return driverRepository.count();
    }

    public long getTotalVehicles() {
        return vehicleRepository.count();
    }

    public long getTotalRoutes() {
        return routeRepository.count();
    }

    public long getCompletedRoutesCount() {
        return routeRepository.countByStatus(true);
    }

    public List<Route> getAllActiveRoutes() {
        return routeRepository.findAllByStatusTrue();
    }

    public long getInactiveVehiclesCount() {
        return vehicleRepository.findVehiclesWithNoRoutes().size();
    }

    public List<Vehicle> getInactiveVehicles() {
        return vehicleRepository.findVehiclesWithNoRoutes();
    }

    public long getActiveVehiclesCount() {
        return vehicleRepository.findVehiclesWithRoutes().size();
    }

    public List<Vehicle> getActiveVehicles() {
        return vehicleRepository.findVehiclesWithRoutes();
    }

    public long getActiveDriversCount() {
        return driverRepository.findDriversWithRoutes().size();
    }

    public List<Driver> getActiveDrivers() {
        return driverRepository.findDriversWithRoutes();
    }

    public long getInactiveDriversCount() {
        return driverRepository.findDriversWithNoRoutes().size();
    }

    public List<Driver> getInactiveDrivers() {
        return driverRepository.findDriversWithNoRoutes();
    }
}
