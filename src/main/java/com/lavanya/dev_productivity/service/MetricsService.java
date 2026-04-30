package com.lavanya.dev_productivity.service;

import com.lavanya.dev_productivity.dto.DeveloperMetric;
import com.lavanya.dev_productivity.dto.Metrics;
import com.lavanya.dev_productivity.dto.MetricsResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class MetricsService {

    private final MetricsInterpreter metricsInterpreter;
    private final List<DeveloperMetric> developerMetrics = List.of(
            metric("DEV-001", "Ava Chen", "Payments API", "2026-03", 2.4, 3.95, 2, 2, 0),
            metric("DEV-001", "Ava Chen", "Payments API", "2026-04", 3.35, 3.9, 2, 2, 0),
            metric("DEV-002", "Noah Patel", "Payments API", "2026-03", 4.3, 5.9, 2, 2, 0),
            metric("DEV-002", "Noah Patel", "Payments API", "2026-04", 3.75, 5.4, 2, 2, 0.5),
            metric("DEV-003", "Mia Lopez", "Checkout Web", "2026-03", 3.1, 4.5, 2, 2, 0.5),
            metric("DEV-003", "Mia Lopez", "Checkout Web", "2026-04", 2.8, 4.1, 2, 2, 0),
            metric("DEV-004", "Lucas Reed", "Checkout Web", "2026-03", 3.6, 5.1, 2, 2, 0),
            metric("DEV-004", "Lucas Reed", "Checkout Web", "2026-04", 4.0, 5.8, 2, 2, 0),
            metric("DEV-005", "Emma Roy", "Mobile Growth", "2026-03", 2.9, 4.2, 2, 2, 0.5),
            metric("DEV-005", "Emma Roy", "Mobile Growth", "2026-04", 3.2, 4.6, 2, 2, 0),
            metric("DEV-006", "Ishan Mehta", "Payments API", "2026-03", 2.35, 3.75, 4, 4, 0),
            metric("DEV-006", "Ishan Mehta", "Payments API", "2026-04", 3.0, 4.2, 2, 2, 0.5),
            metric("DEV-007", "Owen Brooks", "Mobile Growth", "2026-03", 3.8, 5.5, 2, 2, 0.5),
            metric("DEV-007", "Owen Brooks", "Mobile Growth", "2026-04", 3.5, 5.0, 2, 2, 0),
            metric("DEV-008", "Zara Khan", "Checkout Web", "2026-03", 2.6, 3.9, 2, 2, 0),
            metric("DEV-008", "Zara Khan", "Checkout Web", "2026-04", 4.1, 6.1, 2, 2, 0.5)
    );

    public MetricsService(MetricsInterpreter metricsInterpreter) {
        this.metricsInterpreter = metricsInterpreter;
    }

    public MetricsResponse getMetrics(String developerId, String month) {
        DeveloperMetric developerMetric = developerMetrics.stream()
                .filter(metric -> metric.developerId().equalsIgnoreCase(developerId))
                .filter(metric -> metric.month().equals(month))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No metrics found for developerId " + developerId + " and month " + month
                ));

        Metrics metrics = developerMetric.metrics();
        return new MetricsResponse(
                developerMetric.developerName(),
                developerMetric.team(),
                developerMetric.month(),
                metrics,
                metricsInterpreter.interpret(metrics),
                metricsInterpreter.nextSteps(metrics)
        );
    }

    private static DeveloperMetric metric(
            String developerId,
            String developerName,
            String team,
            String month,
            double leadTime,
            double cycleTime,
            int prThroughput,
            int deployFreq,
            double bugRate
    ) {
        return new DeveloperMetric(
                developerId,
                developerName,
                team,
                month,
                new Metrics(leadTime, cycleTime, prThroughput, deployFreq, bugRate)
        );
    }
}
