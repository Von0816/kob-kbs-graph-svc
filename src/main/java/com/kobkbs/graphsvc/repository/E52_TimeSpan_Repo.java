package com.kobkbs.graphsvc.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E52_TimeSpan;

public interface E52_TimeSpan_Repo extends Neo4jRepository<E52_TimeSpan, String> {
  @Query("MATCH (timeSpan:E52_TimeSpan {date_time: $dateTime}) return timeSpan")
  E52_TimeSpan findE52ByDateTime(@Param("dateTime") LocalDateTime dateTime);

  @Query("MATCH (childTS:E52_TimeSpan)-[:P86_falls_within]->(:E52_TimeSpan {date_time: $fallsWithinDT}) RETURN childTS")
  List<E52_TimeSpan> findE52ByFallsWithin(@Param("fallsWithinDT") LocalDateTime fallsWithinDT);

  @Query("MATCH (childTS:E52_TimeSpan {id: $childTSID})-[:P86_falls_within]->(:E52_TimeSpan {id: $parentTSID}) RETURN childTS")
  List<E52_TimeSpan> findP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);

  @Query("MATCH (timeSpan:E52_TimeSpan {id: $timeSpanId}) SET timeSpan.name = $newDateTime")
  E52_TimeSpan updateE52DateTime(@Param("timeSpanId") String timeSpanId, @Param("newDateTime") LocalDateTime newDateTime);
  
  @Query("MATCH (childTS:E52_TimeSpan {id: $childTSID}) WITH childTS MATCH (parentTS:E52_TimeSpan {id: $parentTSID}) MERGE (childTS)-[:P86_falls_within]->(parentTS)")
  void createP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);

  @Query("MATCH (:E52_TimeSpan {id: $childTSID})-[r:P86_falls_within]->(:E52_TimeSpan {id: $parentTSID}) DELETE r")
  void deleteP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);
}
  
