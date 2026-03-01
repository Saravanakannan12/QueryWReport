package com.application.weather.metricsApp.Metric.Repository;

import com.application.weather.metricsApp.Metric.Metric;
import com.application.weather.metricsApp.Metric.SensorMetricId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetricRepository extends JpaRepository<Metric, SensorMetricId> {


}
