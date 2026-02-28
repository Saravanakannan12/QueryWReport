package com.application.weather.metricsApp.Metric;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class SensorMetricDTO {

    @NotBlank(message = "Sensor id is required")
    private Long sensorId;

    @NotBlank(message = "Metric name is required")
    private String metricName;

    @NotBlank(message = "Metric value is required")
    private String metricValue;

}
