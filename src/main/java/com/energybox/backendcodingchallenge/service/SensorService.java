// SensorService.java
package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Sensor createSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public List<Sensor> getSensorsByType(String type) {
        return sensorRepository.findByTypesContains(type);
    }

    public List<String> getLastReadings(Long sensorId) {
        Sensor sensor = sensorRepository.findById(sensorId).orElse(null);
        if (sensor != null) {
            return List.of(sensor.getLastReadingTimestamp(), sensor.getLastReadingValue());
        }
        return List.of();
    }
}
