package com.application.weather.metricsApp.Metric;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Data
public class SensorMetricDTO {

    @NotNull(message = "Sensor id is required")
    private Long sensorId;

    @NotBlank(message = "Metric name is required")
    @Pattern(
            regexp = "^[A-Za-z]+$"
    )
    private String metricName;

    @NotNull(message = "Metric value is required")
    private Double metricValue;

    private LocalDateTime recordedTime;

}
