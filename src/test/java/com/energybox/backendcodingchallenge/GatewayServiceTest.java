package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class GatewayServiceTest {

    @Mock
    private GatewayRepository gatewayRepository;

    @Mock
    private SensorRepository sensorRepository;

    @InjectMocks
    private GatewayService gatewayService;

    private Gateway gateway;
    private Sensor sensor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        gateway = new Gateway();
        gateway.setId(1L);
        gateway.setName("Test Gateway");

        sensor = new Sensor();
        sensor.setId(1L);
        sensor.setName("Test Sensor");
    }

    @Test
    void getAllGateways() {
        when(gatewayRepository.findAll()).thenReturn(Arrays.asList(gateway));

        List<Gateway> result = gatewayService.getAllGateways();

        assertEquals(1, result.size());
        assertEquals("Test Gateway", result.get(0).getName());
    }

    @Test
    void createGateway() {
        when(gatewayRepository.save(any(Gateway.class))).thenReturn(gateway);

        Gateway result = gatewayService.createGateway(new Gateway());

        assertEquals("Test Gateway", result.getName());
    }

    @Test
    void assignSensorToGateway() {
        when(sensorRepository.findById(1L)).thenReturn(Optional.of(sensor));
        when(gatewayRepository.findById(1L)).thenReturn(Optional.of(gateway));

        gatewayService.assignSensorToGateway(1L, 1L);

        verify(sensorRepository, times(1)).save(any(Sensor.class));
    }
}