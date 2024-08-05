// GatewayService.java
package com.energybox.backendcodingchallenge.service;

import com.energybox.backendcodingchallenge.domain.Gateway;
import com.energybox.backendcodingchallenge.domain.Sensor;
import com.energybox.backendcodingchallenge.repository.GatewayRepository;
import com.energybox.backendcodingchallenge.repository.SensorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class GatewayService {

    private final GatewayRepository gatewayRepository;
    private final SensorRepository sensorRepository;

    public GatewayService(GatewayRepository gatewayRepository, SensorRepository sensorRepository) {
        this.gatewayRepository = gatewayRepository;
        this.sensorRepository = sensorRepository;
    }

    public List<Gateway> getAllGateways() {
        return gatewayRepository.findAll();
    }

    public Gateway createGateway(Gateway gateway) {
        return gatewayRepository.save(gateway);
    }

    public void assignSensorToGateway(Long sensorId, Long gatewayId) {
        Optional<Sensor> sensorOpt = sensorRepository.findById(sensorId);
        Optional<Gateway> gatewayOpt = gatewayRepository.findById(gatewayId);
        if (sensorOpt.isPresent() && gatewayOpt.isPresent()) {
            Sensor sensor = sensorOpt.get();
            Gateway gateway = gatewayOpt.get();
            sensor.setGateway(gateway);
            sensorRepository.save(sensor);
        }
    }
}
