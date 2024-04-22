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
public class E22_HumanMadeObject {

  @Id @GeneratedValue(generatorClass = UUIDStringGenerator.class) private String id;
  private String name;
  private String type;
 
  @Relationship(type = "P54_HAS_CURRENT_PERMANENT_LOCATION")
  private Set<E53_Place> currPermaLoc;

  @Relationship(type = "P55_HAS_CURRENT_LOCATION")
  private Set<E53_Place> currLoc;

  @Relationship(type = "P51_HAS_FORMER_OR_CURRENT_OWNER")
  private Set<E21_Person> ownerPerson;

  @Relationship(type = "P51_HAS_FORMER_OR_CURRENT_OWNER")
  private Set<E74_Group> ownerGroup;
}
