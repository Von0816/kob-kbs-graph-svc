package com.kobkbs.graphsvc.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;
import java.time.LocalDateTime;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.Setter;
import lombok.Builder;
import lombok.Getter;

@Node
@Getter
@Setter
@Builder
public class E52_TimeSpan {

  @Id @GeneratedValue(generatorClass = UUIDStringGenerator.class) private String id;
  private LocalDateTime date_time;
 
  @Relationship(type = "P86_falls_within")
  private Set<E52_TimeSpan> fallsWithin;

}
