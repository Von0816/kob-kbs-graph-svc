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
public class E74_Group implements Serializable{

  @Id @GeneratedValue(generatorClass = UUIDStringGenerator.class) private String id;
  private String name;

  @Relationship(type = "P107_HAS_CURRENT_OR_FORMER_MEMBER")
  private Set<E21_Person> member;
}
