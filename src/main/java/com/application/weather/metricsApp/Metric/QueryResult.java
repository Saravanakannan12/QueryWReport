package com.application.weather.metricsApp.Metric;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.List;

@Data
@Component
public class QueryResult {

    List<String> statisticalOperations;

    List<String> metricNames;

    List<Long> sensorIds;

    ZonedDateTime timeRange;
}
