package com.lavanya.dev_productivity.service;

import com.lavanya.dev_productivity.dto.Metrics;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MetricsInterpreter {

    public String interpret(Metrics metrics) {
        if (metrics.leadTime() > 4) {
            return "Lead time is high. Your code is spending extra time between merge and production. Check deployment pipeline delays.";
        }

        if (metrics.cycleTime() > 5) {
            return "Cycle time is elevated. Tickets may be too large, or there are review/merge bottlenecks.";
        }

        if (metrics.bugRate() > 0.3) {
            return "Bug rate is high. Escaped bugs signal gaps in test coverage or edge case handling during review.";
        }

        if (metrics.prThroughput() < 2) {
            return "Low PR throughput. You may be blocked or working on very large changes.";
        }

        return "Your metrics look strong this month. Focus on maintaining consistency.";
    }

    public List<String> nextSteps(Metrics metrics) {
        if (metrics.leadTime() > 4) {
            return List.of(
                    "Review the deployment path for approval or pipeline delays.",
                    "Check whether releases can be batched less often or automated further."
            );
        }

        if (metrics.cycleTime() > 5) {
            return List.of(
                    "Break down large tickets into smaller sub-tasks before starting.",
                    "Check if PR review wait time is contributing to cycle time delay."
            );
        }

        if (metrics.bugRate() > 0.3) {
            return List.of(
                    "Add focused tests for recent escaped bug scenarios.",
                    "Use PR review to call out risky edge cases before merging."
            );
        }

        if (metrics.prThroughput() < 2) {
            return List.of(
                    "Surface blockers early during stand-up or async updates.",
                    "Split large changes into smaller PRs that can be reviewed quickly."
            );
        }

        return List.of(
                "Keep current delivery habits steady through the next sprint.",
                "Share effective practices with teammates who have similar workstreams."
        );
    }
}
