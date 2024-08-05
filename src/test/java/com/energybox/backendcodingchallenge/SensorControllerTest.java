package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.service.SensorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SensorController.class)
public class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SensorService sensorService;

    private Sensor sensor;

    @BeforeEach
    void setUp() {
        sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Test Sensor");
        sensor.setTypes(new HashSet<>(Arrays.asList("temperature")));
    }

    @Test
    void getAllSensors() throws Exception {
        List<Sensor> sensors = Arrays.asList(sensor);
        when(sensorService.getAllSensors()).thenReturn(sensors);

        mockMvc.perform(get("/sensors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Sensor"));
    }

    @Test
    void createSensor() throws Exception {
        when(sensorService.createSensor(any(Sensor.class))).thenReturn(sensor);

        mockMvc.perform(post("/sensors")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Sensor\",\"types\":[\"temperature\"]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Sensor"));
    }

    @Test
    void getSensorsByType() throws Exception {
        List<Sensor> sensors = Arrays.asList(sensor);
        when(sensorService.getSensorsByType("temperature")).thenReturn(sensors);

        mockMvc.perform(get("/sensors/type/temperature"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Sensor"));
    }

    @Test
    void getLastReadings() throws Exception {
        List<String> lastReadings = Arrays.asList("2023-08-05T12:00:00Z", "25.5");
        when(sensorService.getLastReadings(1L)).thenReturn(lastReadings);

        mockMvc.perform(get("/sensors/1/last-readings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").value("2023-08-05T12:00:00Z"))
                .andExpect(jsonPath("$[1]").value("25.5"));
    }
}