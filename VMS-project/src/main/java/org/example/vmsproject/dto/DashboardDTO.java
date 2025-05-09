package org.example.vmsproject.dto;

import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardDTO {
    private long totalVehicles;
    private long activeVehicles;
    private long inactiveVehicles;
    private long totalDrivers;
    private long activeDrivers;
    private long inactiveDrivers;
    private long totalRoutes;
    private long activeRoutes;
    private long inactiveRoutes;
    private Date earliestRouteDate;
    private Date latestRouteDate;
    private List<DriverRouteStatsDTO> driverRouteStats;
    private List<VehicleRouteStatsDTO> vehicleRouteStats;
    private Map<String, Long> driverRouteCounts;
    private Map<String, Long> vehicleRouteCounts;
    private Map<String, Long> driverStatistics;
    private Map<String, Long> vehicleStatistics;
}
