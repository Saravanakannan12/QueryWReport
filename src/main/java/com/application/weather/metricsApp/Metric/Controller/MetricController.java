package com.application.weather.metricsApp.Metric.Controller;

import com.application.weather.metricsApp.Metric.SensorMetricDTO;
import com.application.weather.metricsApp.Metric.Service.MetricService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/metrics")
public class MetricController {

    private MetricService service;

    @PostMapping("/sensor/{sensorId}")
    public void saveMetricData(@RequestBody @Valid SensorMetricDTO metrics){

        service.saveMetricData(metrics);

    }


}
