package com.kobkbs.graphsvc.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.Set;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import lombok.Setter;
import lombok.Builder;
import lombok.Getter;

@Node
@Getter
@Setter
@Builder
public class E5_Event {

  @Id @GeneratedValue(generatorClass = UUIDStringGenerator.class) private String id;
  private String name;

  @Relationship(type = "P7_TOOK_PLACE_AT")
  private Set<E53_Place> location;
  
  @Relationship(type = "P11_HAD_PARTICIPANT")
  private Set<E21_Person> participantPerson;

  @Relationship(type = "P11_HAD_PARTICIPANT")
  private Set<E74_Group> participantGroup;

  @Relationship(type = "P4_HAS_TIME_SPAN")
  private Set<E52_TimeSpan> timeSpan;
}
