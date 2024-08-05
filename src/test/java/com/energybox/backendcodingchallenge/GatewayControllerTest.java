package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.service.GatewayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GatewayController.class)
public class GatewayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GatewayService gatewayService;

    private Gateway gateway;

    @BeforeEach
    void setUp() {
        gateway = new Gateway();
        gateway.setId(1L);
        gateway.setName("Test Gateway");
    }

    @Test
    void getAllGateways() throws Exception {
        List<Gateway> gateways = Arrays.asList(gateway);
        when(gatewayService.getAllGateways()).thenReturn(gateways);

        mockMvc.perform(get("/gateways"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Gateway"));
    }

    @Test
    void createGateway() throws Exception {
        when(gatewayService.createGateway(any(Gateway.class))).thenReturn(gateway);

        mockMvc.perform(post("/gateways")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Test Gateway\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Gateway"));
    }

    @Test
    void assignSensorToGateway() throws Exception {
        mockMvc.perform(post("/gateways/1/sensors/1"))
                .andExpect(status().isOk());
    }
}