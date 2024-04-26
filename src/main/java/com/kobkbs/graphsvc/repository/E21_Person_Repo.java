package com.kobkbs.graphsvc.repository;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E21_Person;

public interface E21_Person_Repo extends Neo4jRepository<E21_Person, String> {
  List<E21_Person> findByName(String name);
  List<E21_Person> findByParentName(String parentName);
  List<E21_Person> findByRightName(String rightName);
  List<E21_Person> findByResidenceName(String residenceName);

  @Query("MATCH (person:E21_Person) WHERE person.name =~ $personName")
  List<E21_Person> findByLikeName(@Param("personName") String personName);

  @Query("MATCH (person:E21_Person {id: $personId})-[:P152_has_parent]->(:E21_Person {id: $parentId}) RETURN person") 
  List<E21_Person> findP152(@Param("personId") String personId, @Param("parentId") String parentId);

  @Query("MATCH (person:E21_Person {id: $personId})-[:P74_has_current_or_former_residence]->(:E53_Place {id: $placeId}) RETURN person")
  List<E21_Person> findP74(@Param("personId") String personId, @Param("placeId") String placeId);

  @Query("MATCH (person:E21_Person {id: $personId})-[:P30_possesses]->(:E30_Right {id: $rightId}) RETURN person")
  List<E21_Person> findP30(@Param("personId") String personId, @Param("rightId") String rightId);

  @Query("MATCH (person:E21_Person {id: $personId}) SET person.name = $newName")
  E21_Person updateE21Name(@Param("personId") String personId, @Param("newName") String newName);

  @Query("MATCH (child:E21_Person {id: $personId}) WITH child MATCH (parent:E21_Person {id: $parentId}) MERGE (child)-[:P152_HAS_PARENT]->(parent)")
  void createP152(@Param("personId") String personId, @Param("parentId") String parentId);

  @Query("MATCH (person:E21_Person {id: $personId}) WITH person MATCH (place:E53_Place {id: $placeId}) MERGE (person)-[:P74_HAS_CURRENT_OR_FORMER_RESIDENCE]->(place)")
  void createP74(@Param("personId") String personId, @Param("placeId") String placeId);

  @Query("MATCH (person:E21_Person {id: $personId}) WITH person MATCH (right:E30_Right {id: $rightId}) MERGE (person)-[:P30_POSSESSES]->(right)")
  void createP30(@Param("personId") String personId, @Param("rightId") String rightId);

  @Query("MATCH (:E21_Person {id: $personId})-[r:P152_HAS_PARENT]->(:E21_Person {id: $parentId}) DELETE r")
  void deleteP152(@Param("personId") String personId, @Param("parentId") String parentId);

  @Query("MATCH (:E21_Person {id: $personId})-[r:P74_HAS_CURRENT_OR_FORMER_RESIDENCE]->(:E53_Place {id: $placeId}) DELETE r")
  void deleteP74(@Param("personId") String personId, @Param("placeId") String placeId);

  @Query("MATCH (:E21_Person {id: $personId})-[r:P30_POSSESSES]->(:E30_Right {id: $rightId}) DELETE r")
  void deleteP30(@Param("personId") String personId, @Param("rightId") String rightId);
}
