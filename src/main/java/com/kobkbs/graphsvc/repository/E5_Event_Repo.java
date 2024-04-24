package com.kobkbs.graphsvc.repository;

import java.util.List;
import java.time.LocalDate;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E5_Event;

public interface E5_Event_Repo extends Neo4jRepository<E5_Event, String> {
  List<E5_Event> findByName(String eventName);
  List<E5_Event> findByLocationName(String placeName);
  List<E5_Event> findByParticipantPersonName(String personName);
  List<E5_Event> findByParticipantGroupName(String groupName);
  List<E5_Event> findByTimeSpan(LocalDate date);

  @Query("MATCH (event:E5_Event {id: $eventId})-[:P7_TOOK_PLACE_AT]->(:E53_Place {id: $placeId}) RETURN event")
  List<E5_Event> findP7(@Param("eventId") String eventId, @Param("placeId") String placeId);


  @Query("MATCH (event:E5_Event {id: $eventId})-[:P11_HAD_PARTICIPANT]->(:E21_Person {id: $personId}) RETURN event")
  List<E5_Event> findP11P(@Param("eventId") String eventId, @Param("personId") String personId);

  @Query("MATCH (event:E5_Event {id: $eventId})-[:P11_HAD_PARTICIPANT]->(:E74_Group {id: $groupId}) RETURN event")
  List<E5_Event> findP11G(@Param("eventId") String eventId, @Param("groupId") String groupId);

  @Query("MATCH (event:E5_Event {id: $eventId})-[:P4_HAS_TIME_SPAN]->(:E52_TimeSpan {id: $timeSpanId}) RETURN event")
  List<E5_Event> findP4(@Param("eventId") String eventId, @Param("timeSpanId") String timeSpanId);

  //update event name
  @Query("MATCH (event:E5_Event {id: $eventId}) SET event.name = $newName")
  void updateE5Name(@Param("eventId") String eventId, @Param("newName") String newName);

  //create P7_TOOK_PLACE_AT
  @Query("MATCH (event:E5_Event {id: $eventId}) WITH event MATCH (place:E53_Place {id: $placeId}) MERGE (event)-[:P7_TOOK_PLACE_AT]->(place)")
  void createP7(@Param("eventId") String eventId, @Param("placeId") String placeId);

  //create P11_HAD_PARTICIPANT for person
  @Query("MATCH (event:E5_Event {id: $eventId}) WITH event MATCH (person:E21_Person {id: $personId}) MERGE (event)-[:P11_HAD_PARTICIPANT]->(person)")
  void createP11P(@Param("eventId") String eventId, @Param("personId") String personId);

  //create P11_HAD_PARTICIPANT for group
  @Query("MATCH (event:E5_Event {id: $eventId}) WITH event MATCH (group:E74_Group {id: $groupId}) MERGE (event)-[:P11_HAD_PARTICIPANT]->(group)")
  void createP11G(@Param("eventId") String eventId, @Param("groupId") String groupId);

  //create P4
  @Query("MATCH (event:E5_Event {id: $eventId}) WITH event MATCH (timeSpan:E52_TimeSpan {id: $timeSpanId}) MERGE (event)-[:P4_HAS_TIME_SPAN]->(timeSpanId)")
  void createP4(@Param("eventId") String eventId, @Param("timeSpanId") String timeSpanId);

  //delete P7_TOOK_PLACE_AT
  @Query("MATCH (:E5_Event {id: $eventId})-[r:P7_TOOK_PLACE_AT]->(:E53_Place {id: $placeId}) DELETE r")
  void deleteP7(@Param("eventId") String eventId, @Param("placeId") String placeId);

  //delete P11_HAD_PARTICIPANT for person
  @Query("MATCH (:E5_Event {id: $eventId})-[r:P11_HAD_PARTICIPANT]->(:E21_Person {id: $personId}) DELETE r")
  void deleteP11P(@Param("eventid") String eventId, @Param("personId") String personId);

  //delete P11_HAD_PARTICIPANT for group
  @Query("MATCH (:E5_Event {id: $eventId})-[r:P11_HAD_PARTICIPANT]->(:E74_Group {id: $groupId}) DELETE r")
  void deleteP11G(@Param("eventId") String eventId, @Param("groupId") String groupId);

  @Query("MATCH (:E5_Event {id: $eventId})-[r:P4_HAS_TIME_SPAN]->(:E52_TimeSpan {id: $timeSpanId}) DELETE r")
  void deleteP4(@Param("eventId") String eventId, @Param("timeSpanId") String timeSpanId);
}
