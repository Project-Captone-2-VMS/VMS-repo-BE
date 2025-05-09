package org.example.vmsproject.dto;

import java.util.Map;

public class RouteStatisticsDTO {

    private String type;
    private Map<String, Long> routeCounts;

    public RouteStatisticsDTO() {}

    public RouteStatisticsDTO(String type, Map<String, Long> routeCounts) {
        this.type = type;
        this.routeCounts = routeCounts;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public Map<String, Long> getRouteCounts() {
        return routeCounts;
    }

    public void setRouteCounts(Map<String, Long> routeCounts) {
        this.routeCounts = routeCounts;
    }

    @Override
    public String toString() {
        return "RouteStatisticsDTO{" +
                "type='" + type + '\'' +
                ", routeCounts=" + routeCounts +
                '}';
    }
}
