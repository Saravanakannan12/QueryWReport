package com.application.weather.metricsApp.Metric;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class SensorMetricId implements Serializable {

    @Column(name= "METRIC_NAME", nullable = false)
    private String metricName;

    //Choosing LocalDateTime here since it is a single location
    @Column(name="RECORDED_TIME", nullable = false)
    private LocalDateTime recordedTime;

    @Column(name="SENSOR_ID", nullable = false)
    private Long sensorId;

    @PrePersist
    public void prePopulate(){
        if(Objects.isNull(recordedTime)) {
            recordedTime = LocalDateTime.now();
        }
    }
}
