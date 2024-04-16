package com.kobkbs.graphsvc.model;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Node
@Getter
@Setter
@Builder
public class E53_Place {

  @Id @GeneratedValue(generatorClass = UUIDStringGenerator.class) private String id;
  private String name;
}
