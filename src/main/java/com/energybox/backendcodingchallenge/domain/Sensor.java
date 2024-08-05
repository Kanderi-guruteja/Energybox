package com.energybox.backendcodingchallenge.domain;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
@Data
public class Sensor {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Relationship(type = "CONNECTED_TO", direction = Relationship.Direction.OUTGOING)
    private Gateway gateway;

    private Set<String> types = new HashSet<>();

    private String lastReadingTimestamp;
    private String lastReadingValue;
}
