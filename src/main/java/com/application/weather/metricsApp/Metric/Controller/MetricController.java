package com.application.weather.metricsApp.Metric.Controller;

import com.application.weather.metricsApp.Metric.SensorMetricDTO;
import com.application.weather.metricsApp.Metric.SensorMetricResponseDTO;
import com.application.weather.metricsApp.Metric.Service.MetricService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class MetricController {

    private final MetricService service;

    @PostMapping("/sensorMetric")
    public ResponseEntity<SensorMetricResponseDTO> saveMetricData(@RequestBody @Valid SensorMetricDTO metricDTO) {

        SensorMetricResponseDTO metricResponseDTO = service.saveMetricData(metricDTO);
        URI uri = URI.create("/v1/sensorMetric/");
        return ResponseEntity.created(uri).body(metricResponseDTO);

    }

}
