package org.example.vmsproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.vmsproject.entity.Driver;
import org.example.vmsproject.entity.Route;
import org.example.vmsproject.entity.Vehicle;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashboardDTO {
    private long totalDrivers;
    private long totalVehicles;
    private long totalRoutes;
    private long totalCompletedRoutes;

    private List<Route> completedRoutes;

    private List<Vehicle> activeVehicles;
    private List<Vehicle> inactiveVehicles;

    private List<Driver> activeDrivers;
    private List<Driver> inactiveDrivers;
}

