package com.kobkbs.graphsvc.model;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.io.Serializable;
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
public class E52_TimeSpan implements Serializable{

  @Id @GeneratedValue(generatorClass = UUIDStringGenerator.class) private String id;
  private String type;
  private String year;
  private int month;
  private int day;
 
  @Relationship(type = "P86_FALLS_WITHIN")
  private Set<E52_TimeSpan> fallsWithin;
}
