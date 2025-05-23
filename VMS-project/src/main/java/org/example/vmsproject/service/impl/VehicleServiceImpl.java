package org.example.vmsproject.service.impl;

import org.example.vmsproject.dto.VehicleDTO;
import org.example.vmsproject.entity.Vehicle;
import org.example.vmsproject.exception.AppException;
import org.example.vmsproject.exception.ErrorCode;
import org.example.vmsproject.repository.VehicleRepository;
import org.example.vmsproject.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> getVehicleById(long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public String updateVehicle(long id, VehicleDTO vehicleDTO) {
        return vehicleRepository.findById(id).map(vehicle-> {
            vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
            vehicle.setType(vehicleDTO.getType());
            vehicle.setCapacity(vehicleDTO.getCapacity());
            vehicle.setMaintenanceSchedule(vehicleDTO.getMaintenanceSchedule());
            vehicleRepository.save(vehicle);
            return "Vehicle updated successfully!";
        }).orElse("Vehicle not found!");
    }

    @Override
    public String addVehicle(VehicleDTO vehicleDTO) {
        Vehicle vehicle = new Vehicle();
        if(vehicleRepository.existsByLicensePlate(vehicleDTO.getLicensePlate())){
            throw new AppException(ErrorCode.LICENSE_PLATE_EXISTS);
        }
        vehicle.setLicensePlate(vehicleDTO.getLicensePlate());
        vehicle.setType(vehicleDTO.getType());
        vehicle.setCapacity(vehicleDTO.getCapacity());
        vehicle.setStatus(false);
        vehicle.setMaintenanceSchedule(vehicleDTO.getMaintenanceSchedule());
        vehicleRepository.save(vehicle);
        return "Vehicle added successfully!";
    }

    @Override
    public String deleteVehicle(long id) {
        vehicleRepository.deleteById(id);
        return "Vehicle deleted successfully!";
    }

    @Override
    public List<Vehicle> getAllVehiclesNoActive() {
        return vehicleRepository.findAllVehicleNoActive();
    }

    public int totalVehicles() {
        return vehicleRepository.findTotalVehicles();
    }
}
