package com.application.weather.metricsApp.Metric.Service;

import com.application.weather.metricsApp.Metric.Repository.MetricRepository;
import com.application.weather.metricsApp.Metric.SensorMetricDTO;
import com.application.weather.metricsApp.MetricMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@AllArgsConstructor
public class MetricService {

    private final MetricMapper metricMapper;
    private final MetricRepository repository;

    public void saveMetricData(SensorMetricDTO metricsDTO) {
        metricsDTO.setRecordedTime(LocalDateTime.now());
        repository.save(Objects.requireNonNull(metricMapper.toMetricEntity(metricsDTO)));

        //TODO -> Might throws Null Pointer exception
    }
}
