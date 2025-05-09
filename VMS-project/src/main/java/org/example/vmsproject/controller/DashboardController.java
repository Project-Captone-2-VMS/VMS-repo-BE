package org.example.vmsproject.controller;

import org.example.vmsproject.dto.DashboardDTO;
import org.example.vmsproject.service.impl.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;


    @GetMapping("/stats")
    public ResponseEntity<DashboardDTO> getStats() {
        DashboardDTO stats = dashboardService.getDashboard();
        return ResponseEntity.ok(stats);
    }
}

