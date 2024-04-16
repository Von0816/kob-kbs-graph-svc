package com.kobkbs.graphsvc.repository;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E52_TimeSpan;
import com.kobkbs.graphsvc.model.E5_Event;

public interface E5_Event_Repo extends Neo4jRepository<E5_Event, String> {
  E5_Event findByName(String eventName);
  List<E5_Event> findByLocationName(String placeName);
  List<E5_Event> findByParticipantPersonName(String personName);
  List<E5_Event> findByParticipantGroupName(String groupName);

  @Query("MATCH (event:E5_Event {id: $eventId})-[:P7_took_place_at]->(:E53_Place {id: $placeId}) RETURN event")
  List<E5_Event> findP7(@Param("eventId") String eventId, @Param("placeId") String placeId);


  @Query("MATCH (event:E5_Event {id: $eventId})-[:P11_had_participant]->(:E21_Person {id: $personId}) RETURN event")
  List<E5_Event> findP11P(@Param("eventId") String eventId, @Param("personId") String personId);

  @Query("MATCH (event:E5_Event {id: $eventId})-[:P11_had_participant]->(:E74_Group {id: $groupId}) RETURN event")
  List<E5_Event> findP11G(@Param("eventId") String eventId, @Param("groupId") String groupId);

  //update event name
  @Query("MATCH (event:E5_Event {id: $eventId}) SET event.name = $newName")
  void updateE5Name(@Param("eventId") String eventId, @Param("newName") String newName);

  //create P7_took_place_at
  @Query("MATCH (event:E5_Event {id: $eventId}) WITH event MATCH (place:E53_Place {id: $placeId}) MERGE (event)-[:P7_took_place_at]->(place)")
  void createP7(@Param("eventId") String eventId, @Param("placeId") String placeId);

  //create P11_had_participant for person
  @Query("MATCH (event:E5_Event {id: $eventId}) WITH event MATCH (person:E21_Person {id: $personId}) MERGE (event)-[:P11_had_participant]->(person)")
  void createP11P(@Param("eventId") String eventId, @Param("personId") String personId);

  //create P11_had_participant for group
  @Query("MATCH (event:E5_Event {id: $eventId}) WITH event MATCH (group:E74_Group {id: $groupId}) MERGE (event)-[:P11_had_participant]->(group)")
  void createP11G(@Param("eventId") String eventId, @Param("groupId") String groupId);

  //delete P7_took_place_at
  @Query("MATCH (:E5_Event {id: $eventId})-[r:P7_took_place_at]->(:E53_Place {id: $placeId}) DELETE r")
  void deleteP7(@Param("eventId") String eventId, @Param("placeId") String placeId);

  //delete P11_had_participant for person
  @Query("MATCH (:E5_Event {id: $eventId})-[r:P11_had_participant]->(:E21_Person {id: $personId}) DELETE r")
  void deleteP11P(@Param("eventid") String eventId, @Param("personId") String personId);

  //delete P11_had_participant for group
  @Query("MATCH (:E5_Event {id: $eventId})-[r:P11_had_participant]->(:E74_Group {id: $groupId}) DELETE r")
  void deleteP11G(@Param("eventId") String eventId, @Param("groupId") String groupId);
}
