package com.lavanya.dev_productivity.controller;

import com.lavanya.dev_productivity.dto.MetricsResponse;
import com.lavanya.dev_productivity.service.MetricsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class MetricsController {

    private final MetricsService metricsService;

    public MetricsController(MetricsService metricsService) {
        this.metricsService = metricsService;
    }

    @GetMapping("/api/metrics")
    public MetricsResponse getMetrics(
            @RequestParam String developerId,
            @RequestParam String month
    ) {
        return metricsService.getMetrics(developerId, month);
    }
}
