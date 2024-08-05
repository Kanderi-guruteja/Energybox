package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.service.GatewayService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/gateways")
public class GatewayController {

    private final GatewayService service;

    public GatewayController(GatewayService service) {
        this.service = service;
    }

    @ApiOperation(value = "Get all gateways")
    @GetMapping
    public ResponseEntity<List<Gateway>> getAllGateways() {
        return new ResponseEntity<>(service.getAllGateways(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new gateway")
    @PostMapping
    public ResponseEntity<Gateway> createGateway(@RequestBody Gateway gateway) {
        return new ResponseEntity<>(service.createGateway(gateway), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Assign sensor to a gateway")
    @PostMapping("/{gatewayId}/sensors/{sensorId}")
    public ResponseEntity<Void> assignSensorToGateway(@PathVariable Long gatewayId, @PathVariable Long sensorId) throws IOException, InterruptedException {
        service.assignSensorToGateway(sensorId, gatewayId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
