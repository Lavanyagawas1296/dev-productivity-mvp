package com.lavanya.dev_productivity.dto;

import java.util.List;

public record MetricsResponse(
        String developerName,
        String team,
        String month,
        Metrics metrics,
        String interpretation,
        List<String> nextSteps
) {
}
