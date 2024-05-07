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
public class E21_Person implements Serializable{

  @Id @GeneratedValue(generatorClass = UUIDStringGenerator.class) private String id;
  private String name;

  @Relationship(type = "P152_HAS_PARENT")
  private Set<E21_Person> parent;

  @Relationship(type = "P74_HAS_CURRENT_OR_FORMER_RESIDENCE")
  private Set<E53_Place> residence; 

  @Relationship(type = "P30_POSSESSES")
  private Set<E30_Right> right;
}

