package com.application.weather.metricsApp.Metric;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MetricServiceTest {

    @Mock
    private MetricMapper metricMapper;

    @Mock
    private MetricRepository repository;

    @InjectMocks
    private MetricService metricService;

    private SensorMetricDTO sensorMetricDTO;
    private Metric metric;
    private SensorMetricResponseDTO responseDTO;

    @BeforeEach
    public void setup() {

        sensorMetricDTO = new SensorMetricDTO();
        sensorMetricDTO.setMetricName("temperature");
        sensorMetricDTO.setMetricValue(22.00);
        sensorMetricDTO.setSensorId(1L);

         metric = Metric.builder().id(SensorMetricId.builder()
                        .metricName("temperature")
                        .sensorId(1L)
                        .build())
                .metricValue(22.00)
                .build();


        responseDTO = new SensorMetricResponseDTO();
        responseDTO.setMetricName("temperature");
        responseDTO.setMetricValue(22.00);
        responseDTO.setSensorId(1L);
    }

    @Test
    public void givenAValidDTOWhenSaveThenReturnADTO() {

        when(metricMapper.toMetricEntity(any(SensorMetricDTO.class))).thenReturn(metric);
        when(repository.save(any(Metric.class))).thenReturn(metric);
        when(metricMapper.toMetricDTO(any(Metric.class))).thenReturn(responseDTO);

        SensorMetricResponseDTO result = metricService.saveMetricData(sensorMetricDTO);

        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.getMetricValue()).isEqualTo(22.00);
        verify(repository, times(1)).save(any(Metric.class));
        verify(metricMapper, times(1)).toMetricEntity(any(SensorMetricDTO.class));
        verify(metricMapper, times(1)).toMetricDTO(any(Metric.class));
    }

    @Test
    public void givenANullInputWhenSaveThenThrowANullPointerException() {

        Assertions.assertThatThrownBy(() -> metricService.saveMetricData(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("null");
    }

    @Test
    public void givenAValidInputWhenANullMapperThenThrowANPE() {

        when(metricMapper.toMetricEntity(any(SensorMetricDTO.class))).thenReturn(null);

        Assertions.assertThatThrownBy(() -> metricService.saveMetricData(sensorMetricDTO))
                .isInstanceOf(NullPointerException.class);
        verify(repository, never()).save(any(Metric.class));
        verify(metricMapper, times(1)).toMetricEntity(any(SensorMetricDTO.class));
    }

    @Test
    public void givenAValidInputWhenSaveFailedThenReturnDatabaseError() {

        when(metricMapper.toMetricEntity(any(SensorMetricDTO.class))).thenReturn(metric);
        when(repository.save(any(Metric.class))).thenThrow(new RuntimeException("Database error"));

        Assertions.assertThatThrownBy(() -> metricService.saveMetricData(sensorMetricDTO))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Database error");
        verify(metricMapper, times(1)).toMetricEntity(any(SensorMetricDTO.class));
        verify(repository, times(1)).save(any(Metric.class));
    }
}