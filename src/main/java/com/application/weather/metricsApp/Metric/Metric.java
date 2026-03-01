package com.application.weather.metricsApp.Metric;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@Table(name= "SENSOR_METRIC")
public class Metric {

    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "sensorId", column = @Column(name = "SENSOR_ID", nullable = false)),
            @AttributeOverride(name = "metricName", column = @Column(name = "METRIC_NAME", nullable = false)),
            @AttributeOverride(name = "recordedTime", column = @Column(name = "RECORDED_TIME", nullable = false)),
            })
    private SensorMetricId id;

    @Column(name= "METRIC_VALUE", nullable = false)
    private double metricValue;

}
