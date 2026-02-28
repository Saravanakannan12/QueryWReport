package com.application.weather.metricsApp;

import com.application.weather.metricsApp.Metric.Metric;
import com.application.weather.metricsApp.Metric.SensorMetricDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MetricMapper {

    Metric toMetricEntity(SensorMetricDTO metricDTO);
}
