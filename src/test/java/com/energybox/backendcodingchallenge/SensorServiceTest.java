package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class SensorServiceTest {

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private SensorService sensorService;

    private Sensor sensor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Test Sensor");
        sensor.setTypes(new HashSet<>(Arrays.asList("temperature")));
        sensor.setLastReadingTimestamp("2023-08-05T12:00:00Z");
        sensor.setLastReadingValue("25.5");
    }

    @Test
    void getAllSensors() {
        when(sensorRepository.findAll()).thenReturn(Arrays.asList(sensor));

        List<Sensor> result = sensorService.getAllSensors();

        assertEquals(1, result.size());
        assertEquals("Test Sensor", result.get(0).getName());
    }

    @Test
    void createSensor() {
        when(sensorRepository.save(any(Sensor.class))).thenReturn(sensor);

        Sensor result = sensorService.createSensor(new Sensor());

        assertEquals("Test Sensor", result.getName());
    }

    @Test
    void getSensorsByType() {
        when(sensorRepository.findByTypesContains("temperature")).thenReturn(Arrays.asList(sensor));

        List<Sensor> result = sensorService.getSensorsByType("temperature");

        assertEquals(1, result.size());
        assertEquals("Test Sensor", result.get(0).getName());
    }

    @Test
    void getLastReadings() {
        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));

        List<String> result = sensorService.getLastReadings(1L);

        assertEquals(2, result.size());
        assertEquals("2023-08-05T12:00:00Z", result.get(0));
        assertEquals("25.5", result.get(1));
    }
}