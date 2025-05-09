package org.example.vmsproject.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RouteSummaryDTO {
    private Long routeId;
    private String startLocationName;
    private String endLocationName;
    private int totalDistance;
    private int totalTime;
    private Date routeDate;
    private Boolean status;
}

