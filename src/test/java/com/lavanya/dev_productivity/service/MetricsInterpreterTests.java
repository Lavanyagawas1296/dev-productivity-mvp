package com.lavanya.dev_productivity.service;

import com.lavanya.dev_productivity.dto.Metrics;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MetricsInterpreterTests {

    private final MetricsInterpreter interpreter = new MetricsInterpreter();

    @Test
    void prioritizesCycleTimeAfterLeadTime() {
        Metrics metrics = new Metrics(3.75, 5.4, 2, 2, 0.5);

        assertThat(interpreter.interpret(metrics))
                .isEqualTo("Cycle time is elevated. Tickets may be too large, or there are review/merge bottlenecks.");
    }

    @Test
    void returnsHealthyMessageWhenNoRuleMatches() {
        Metrics metrics = new Metrics(3.0, 4.0, 2, 2, 0);

        assertThat(interpreter.interpret(metrics))
                .isEqualTo("Your metrics look strong this month. Focus on maintaining consistency.");
    }
}
