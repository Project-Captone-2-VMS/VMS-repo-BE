package org.example.vmsproject.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.sql.Time;


@Data
@AllArgsConstructor
public class FindSequenceRequest {
    private double startLat;
    private double startLng;
    private String destinations;
    private double endLat;
    private double endLng;
    private Long driverId;
    private Long vehicleId;
    private LocalDate startDate;
    private LocalTime startTime;
    private String polyline;
}
