package com.application.weather.metricsApp.Metric;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.Objects;

@Component
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name= "")
public class Metric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long metricId;

    @Column(nullable = false)
    private String metricName;

    @Column(nullable = false)
    private double metricValue;

    @Column(nullable = false)
    private ZonedDateTime recordedDate;

    @Column(nullable = false)
    private Long sensorId;

    @PrePersist
    public void prePopulate(){
        if(Objects.isNull(recordedDate)) {
            recordedDate = ZonedDateTime.now();
        }
    }

}
