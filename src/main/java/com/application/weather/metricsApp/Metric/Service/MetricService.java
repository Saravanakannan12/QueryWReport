package com.application.weather.metricsApp.Metric.Service;

import com.application.weather.metricsApp.Metric.SensorMetricDTO;
import com.application.weather.metricsApp.Metric.Repository.MetricRepository;
import com.application.weather.metricsApp.MetricMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class MetricService {

    private MetricMapper metricMapper;
    private MetricRepository repository;

    public void saveMetricData(SensorMetricDTO metricsDTO) {
        repository.save(Objects.requireNonNull(metricMapper.toMetricEntity(metricsDTO)));

        //TODO -> Might throws Null Pointer exception
    }
}
