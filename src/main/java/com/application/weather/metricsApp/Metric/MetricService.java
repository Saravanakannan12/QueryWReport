package com.application.weather.metricsApp.Metric;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
@Slf4j
public class MetricService {

    private final MetricMapper metricMapper;
    private final MetricRepository repository;

    public SensorMetricResponseDTO saveMetricData(SensorMetricDTO metricsDTO) {
        metricsDTO.setRecordedTime(LocalDateTime.now());
        log.info("Saving metrics to Database {}", metricsDTO);
        Metric savedMetric = repository.save(Objects.requireNonNull(metricMapper.toMetricEntity(metricsDTO)));
        return metricMapper.toMetricDTO(savedMetric);
    }
}
