package com.application.weather.metricsApp.Metric.Controller;

import com.application.weather.metricsApp.Metric.SensorMetricDTO;
import com.application.weather.metricsApp.Metric.Service.MetricService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MetricController {

    private final MetricService service;

    @PostMapping("/sensor_metric")
    public void saveMetricData(@RequestBody @Valid SensorMetricDTO metrics){

        service.saveMetricData(metrics);

    }


}
