package com.energybox.backendcodingchallenge.controller;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.service.SensorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @ApiOperation(value = "Get all sensors")
    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        return new ResponseEntity<>(sensorService.getAllSensors(), HttpStatus.OK);
    }

    @ApiOperation(value = "Create a new sensor")
    @PostMapping
    public ResponseEntity<Sensor> createSensor(@RequestBody Sensor sensor) {
        return new ResponseEntity<>(sensorService.createSensor(sensor), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get sensors by type")
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Sensor>> getSensorsByType(@PathVariable String type) {
        return new ResponseEntity<>(sensorService.getSensorsByType(type), HttpStatus.OK);
    }

    @ApiOperation(value = "Get last readings of a sensor")
    @GetMapping("/{sensorId}/last-readings")
    public ResponseEntity<List<String>> getLastReadings(@PathVariable Long sensorId) {
        return new ResponseEntity<>(sensorService.getLastReadings(sensorId), HttpStatus.OK);
    }
}
