package com.lavanya.dev_productivity.dto;

public record Metrics(
        double leadTime,
        double cycleTime,
        int prThroughput,
        int deploymentFrequency,
        double bugRate
) {
}
