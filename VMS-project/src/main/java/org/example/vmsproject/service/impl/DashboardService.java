package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.DashboardDTO;
import org.example.vmsproject.dto.RouteStatisticsDTO;
import org.example.vmsproject.repository.DriverRepository;
import org.example.vmsproject.repository.RouteRepository;
import org.example.vmsproject.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private RouteRepository routeRepository;


    public DashboardDTO getDashboard() {
        DashboardDTO dto = new DashboardDTO();

        // Vehicles statistics
        dto.setTotalVehicles(vehicleRepository.count());
        dto.setActiveVehicles(vehicleRepository.countByStatus(true));
        dto.setInactiveVehicles(vehicleRepository.countByStatus(false));

        // Drivers statistics
        dto.setTotalDrivers(driverRepository.count());
        dto.setActiveDrivers(driverRepository.countByStatus(true));
        dto.setInactiveDrivers(driverRepository.countByStatus(false));

        // Routes statistics
        dto.setTotalRoutes(routeRepository.count());
        dto.setActiveRoutes(routeRepository.countByStatus(true));
        dto.setInactiveRoutes(routeRepository.countByStatus(false));
        dto.setEarliestRouteDate(routeRepository.findMinRouteDate());
        dto.setLatestRouteDate(routeRepository.findMaxRouteDate());


//        Map<String, Long> driverRouteCounts = routeRepository.getDriverRouteCounts();
//
//
//        Map<String, Long> topDrivers = getTopDrivers(driverRouteCounts, 5);
//        dto.setDriverStatistics(getRouteStatistics(topDrivers, "driver"));
//
//
//        Map<String, Long> vehicleRouteCounts = routeRepository.getVehicleRouteCounts();
//
//
//        Map<String, Long> topVehicles = getTopVehicles(vehicleRouteCounts, 5);
//        dto.setVehicleStatistics(getRouteStatistics(topVehicles, "vehicle"));

        return dto;
    }


//    private Map<String, Long> getTopDrivers(Map<String, Long> driverRouteCounts, int limit) {
//        return driverRouteCounts.entrySet()
//                .stream()
//                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue())) // Sắp xếp theo số lượng route giảm dần
//                .limit(limit) // Lấy 5 tài xế có số route nhiều nhất
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }
//
//    private Map<String, Long> getTopVehicles(Map<String, Long> vehicleRouteCounts, int limit) {
//        return vehicleRouteCounts.entrySet()
//                .stream()
//                .sorted((entry1, entry2) -> Long.compare(entry2.getValue(), entry1.getValue())) // Sắp xếp theo số lượng route giảm dần
//                .limit(limit) // Lấy 5 xe có số route nhiều nhất
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }
//    private Map<String, Long> getRouteStatistics(Map<String, Long> routeCounts, String type) {
//        RouteStatisticsDTO statisticsDTO = new RouteStatisticsDTO();
//        statisticsDTO.setType(type);
//        statisticsDTO.setRouteCounts(routeCounts);
//        return (Map<String, Long>) statisticsDTO;
//    }


}

//public DashboardDTO getDashboard() {
//    DashboardDTO dto = new DashboardDTO();
//
//    // Vehicles
//    dto.setTotalVehicles(vehicleRepository.count());
//    dto.setActiveVehicles(vehicleRepository.countByStatus(true));
//    dto.setInactiveVehicles(vehicleRepository.countByStatus(false));
//
//    // Drivers
//    dto.setTotalDrivers(driverRepository.count());
//    dto.setActiveDrivers(driverRepository.countByStatus(true));
//    dto.setInactiveDrivers(driverRepository.countByStatus(false));
//
//    // Routes
//    dto.setTotalRoutes(routeRepository.count());
//    dto.setActiveRoutes(routeRepository.countByStatus(true));
//    dto.setInactiveRoutes(routeRepository.countByStatus(false));
//    dto.setEarliestRouteDate(routeRepository.findMinRouteDate());
//    dto.setLatestRouteDate(routeRepository.findMaxRouteDate());
//
//    return dto;
//}
