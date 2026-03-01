package com.application.weather.metricsApp.Metric;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SensorMetricResponseDTO {

    @NotNull(message = "Sensor id is required")
    private Long sensorId;

    @NotBlank(message = "Metric name is required")
    private String metricName;

    @NotNull(message = "Metric value is required")
    private double metricValue;
}
