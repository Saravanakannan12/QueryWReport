package com.application.weather.metricsApp.Metric.Service;

import com.application.weather.metricsApp.Metric.Metric;
import com.application.weather.metricsApp.Metric.Repository.MetricRepository;
import com.application.weather.metricsApp.Metric.SensorMetricDTO;
import com.application.weather.metricsApp.Metric.SensorMetricResponseDTO;
import com.application.weather.metricsApp.MetricMapper;
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
        //TODO -> Might throw Null Pointer exception
    }
}
