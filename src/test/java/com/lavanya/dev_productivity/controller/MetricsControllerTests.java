package com.lavanya.dev_productivity.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MetricsControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void returnsMetricsForDeveloperAndMonth() throws Exception {
        mockMvc.perform(get("/api/metrics")
                        .param("developerId", "DEV-002")
                        .param("month", "2026-04")
                        .header("Origin", "http://localhost:3000"))
                .andExpect(status().isOk())
                .andExpect(header().string("Access-Control-Allow-Origin", "http://localhost:3000"))
                .andExpect(jsonPath("$.developerName").value("Noah Patel"))
                .andExpect(jsonPath("$.team").value("Payments API"))
                .andExpect(jsonPath("$.metrics.cycleTime").value(5.4))
                .andExpect(jsonPath("$.interpretation").value("Cycle time is elevated. Tickets may be too large, or there are review/merge bottlenecks."))
                .andExpect(jsonPath("$.nextSteps[0]").value("Break down large tickets into smaller sub-tasks before starting."));
    }
}
