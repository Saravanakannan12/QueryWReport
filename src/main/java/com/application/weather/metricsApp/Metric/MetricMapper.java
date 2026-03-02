package com.application.weather.metricsApp.Metric;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@SuppressWarnings("unused")
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MetricMapper {

    @Mapping(target = "id.sensorId", source = "sensorId")
    @Mapping(target = "id.metricName", source = "metricName")
    @Mapping(target = "id.recordedTime", source = "recordedTime")
    Metric toMetricEntity(SensorMetricDTO metricDTO);


    @Mapping(source = "id.sensorId", target = "sensorId")
    @Mapping(source = "id.metricName", target = "metricName")
    SensorMetricResponseDTO toMetricDTO(Metric metric);


}
