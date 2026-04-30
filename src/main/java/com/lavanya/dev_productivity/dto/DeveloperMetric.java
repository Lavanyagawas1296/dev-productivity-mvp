package com.lavanya.dev_productivity.dto;

public record DeveloperMetric(
        String developerId,
        String developerName,
        String team,
        String month,
        Metrics metrics
) {
}
