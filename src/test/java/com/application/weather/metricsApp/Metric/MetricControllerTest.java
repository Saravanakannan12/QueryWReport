package com.application.weather.metricsApp.Metric;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MetricControllerTest {
    private MockMvc mockMvc;

    @Mock
    private MetricService metricService;

    @InjectMocks
    private MetricController metricController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(metricController).build();
        this.objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    public void givenAValidMetricWhenSaveThenReturnIsCreated() throws Exception {

        SensorMetricDTO metricDTO = new SensorMetricDTO();
        metricDTO.setMetricName("metricName");
        metricDTO.setMetricValue(20.00);
        metricDTO.setSensorId(1L);
        metricDTO.setRecordedTime(LocalDateTime.now());

        SensorMetricResponseDTO metricResponseDTO = new SensorMetricResponseDTO();

        metricResponseDTO.setMetricName("metricName");
        metricResponseDTO.setMetricValue(20.00);
        metricResponseDTO.setSensorId(1L);


        when(metricService.saveMetricData(any(SensorMetricDTO.class))).thenReturn(metricResponseDTO);

        mockMvc.perform(post("/v1/sensorMetric")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(metricDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenAnInValidMetricWhenPostThenReturnABadRequest() throws Exception {
        SensorMetricDTO invalidMetricDTO = new SensorMetricDTO();

        mockMvc.perform(post("/v1/sensorMetric")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidMetricDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void givenAnUnexpectedInputWhenSaveThenReturnBadRequest() throws Exception {
        SensorMetricDTO validMetricDTO = new SensorMetricDTO();

        when(metricService.saveMetricData(any(SensorMetricDTO.class))).thenThrow(new ConstraintViolationException("Validation failed", null));

        mockMvc.perform(post("/v1/sensorMetric")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validMetricDTO)))
                .andExpect(status().isBadRequest());
    }

}