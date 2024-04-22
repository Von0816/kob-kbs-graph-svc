package com.kobkbs.graphsvc.repository;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E22_HumanMadeObject;

public interface E22_HumanMadeObject_Repo extends Neo4jRepository<E22_HumanMadeObject, String> {
  E22_HumanMadeObject findByName(String hmoName);
  List<E22_HumanMadeObject> findByType(String hmoType);
  List<E22_HumanMadeObject> findByCurrPermaLocName(String currPermaLocName);
  List<E22_HumanMadeObject> findByCurrLocName(String currLocName);
  List<E22_HumanMadeObject> findByOwnerPersonName(String ownerPersonName);
  List<E22_HumanMadeObject> findByOwnerGroupName(String ownerGroupName);

  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId})-[:P54_HAS_CURRENT_PERMANENT_LOCATION]->(:E53_Place {id: $placeId}) RETURN hmo")
  List<E22_HumanMadeObject> findP54(@Param("hmoId") String hmoId, @Param("placeId") String placeId);

  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId})-[:P55_HAS_CURRENT_LOCATION]->(:E53_Place {id: $placeId}) RETURN hmo")
  List<E22_HumanMadeObject> findP55(@Param("hmoId") String hmoId, @Param("placeId") String placeId);

  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId})-[:P51_HAS_CURRENT_OR_FORMER_OWNER]->(:E21_Person {id: $personId}) RETURN hmo")
  List<E22_HumanMadeObject> findP51P(@Param("hmoId") String hmoId, @Param("personId") String personId);

  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId})-[:P51_HAS_CURRENT_OR_FORMER_OWNER]->(:E74_Group {id: $groupId}) RETURN hmo")
  List<E22_HumanMadeObject> findP51G(@Param("hmoId") String hmoId, @Param("groupId") String groupId);

  //update e22 human made object name
  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId}) SET hmo.name = $newName")
  void updateE22Name(@Param("hmoId") String hmoId, @Param("newName") String newName);

  //create p54 has current permanent location relationship
  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId}) WITH hmo MATCH (place:E53_Place {id: $placeId}) MERGE (hmo)-[:P54_HAS_CURRENT_PERMANENT_LOCATION]->(place)")
  void createP54(@Param("hmoId") String hmoId, @Param("placeId") String placeId);

  //create p55 has current location relationship
  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId}) WITH hmo MATCH (place:E53_Place {id: $placeId}) MERGE (hmo)-[:P55_HAS_CURRENT_LOCATION]->(place)")
  void createP55(@Param("hmoId") String hmoId, @Param("placeId") String placeId);

  //create p51 has current or former owner (person) relationship
  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId}) WITH hmo MATCH (person:E21_Person {id: $personId}) MERGE (hmo)-[:P51_HAS_CURRENT_OR_FORMER_OWNER]->(person)")
  void createP51P(@Param("hmoId") String hmoId, @Param("personId") String personId);

  //create p51 has current or former owner (group) relationship
  @Query("MATCH (hmo:E22_HumanMadeObject {id: $hmoId}) WITH hmo MATCH (group:E74_Group {id: $groupId}) MERGE (hmo)-[:P51_HAS_CURRENT_OR_FORMER_OWNER]->(group)")
  void createP51G(@Param("hmoId") String hmoId, @Param("groupId") String groupId);

  //delete p54 has current permanent location relationship
  @Query("MATCH (:E22_HumanMadeObject {id: $hmoId})-[r:P54_HAS_CURRENT_PERMANENT_LOCATION]->(:E53_Place {id: $placeId}) DELETE r")
  void deleteP54(@Param("hmoId") String hmoId, @Param("placeId") String placeId);
  
  //delete p55 has current location relationship
  @Query("MATCH (:E22_HumanMadeObject {id: $hmoId})-[r:P55_HAS_CURRENT_LOCATION]->(:E53_Place {id: $placeId}) DELETE r")
  void deleteP55(@Param("hmoId") String hmoId, @Param("placeId") String placeId);

  //delete p51 has current or former owner (person) relationship
  @Query("MATCH (:E22_HumanMadeObject {id: $hmoId})-[r:P51_HAS_CURRENT_OR_FORMER_OWNER]->(:E21_Person {id: $personId}) DELETE r")
  void deleteP51P(@Param("hmoId") String hmoId, @Param("personId") String personId);

  //delete p51 has current or former owner (group) relationship
  @Query("MATCH (:E22_HumanMadeObject {id: $hmoId})-[r:P51_HAS_CURRENT_OR_FORMER_OWNER]->(:E74_Group {id: $groupId}) DELETE r")
  void deleteP51G(@Param("hmoId") String hmoId, @Param("groupId") String groupId);
}

