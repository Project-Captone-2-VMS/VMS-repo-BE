package org.example.vmsproject.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Vehicle;
import org.example.vmsproject.repository.DriverRepository;
import org.example.vmsproject.repository.RouteRepository;
import org.example.vmsproject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardService {

    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private RouteRepository routeRepository;

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
        return vehicleRepository.findVehicleByStatusFalse().size();
    }

    public List<Vehicle> getInactiveVehicles() {
        return vehicleRepository.findVehiclesWithNoRoutes();
    }

    public long getActiveVehiclesCount() {
        return vehicleRepository.findVehicleByStatusTrue().size();
    }

    public List<Vehicle> getActiveVehicles() {
        return vehicleRepository.findVehiclesWithRoutes();
    }

    public long getActiveDriversCount() {return driverRepository.findAllDriverByStatusTrue().size();
    }

    public List<Driver> getActiveDrivers() {
        return driverRepository.findDriversWithRoutes();
    }

    public long getInactiveDriversCount() {
        return driverRepository.findAllDriverByStatusFalse().size();
    }

    public List<Driver> getInactiveDrivers() {
        return driverRepository.findDriversWithNoRoutes();
    }
}
