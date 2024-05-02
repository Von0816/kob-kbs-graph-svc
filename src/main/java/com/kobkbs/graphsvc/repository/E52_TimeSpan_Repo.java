package com.kobkbs.graphsvc.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E52_TimeSpan;

public interface E52_TimeSpan_Repo extends Neo4jRepository<E52_TimeSpan, String> {

  @Query("MATCH (timeSpan:E52_TimeSpan) return timeSpan")
  List<E52_TimeSpan> findAllE52();

  @Query("MATCH (timeSpan:E52_TimeSpan {year: $year} RETURN timeSpan)") 
  List<E52_TimeSpan> findE52ByYear(@Param("year") String year);

  @Query("Match (timeSpan:E52_TimeSpan {month: $month}) RETURN timeSpan")
  List<E52_TimeSpan> findE52ByMonth(@Param("month") int month);

  @Query("MATCH (timeSpan:E52_TimeSpan {date_time: $dateTime}) return timeSpan")
  E52_TimeSpan findE52ByDate(@Param("dateTime") LocalDate dateTime);

  @Query("MATCH (childTS:E52_TimeSpan)-[:P86_FALLS_WITHIN]->(:E52_TimeSpan {date_time: $fallsWithinDT}) RETURN childTS")
  List<E52_TimeSpan> findE52ByFallsWithin(@Param("fallsWithinDT") LocalDate fallsWithinDT);

  @Query("MATCH (childTS:E52_TimeSpan {id: $childTSID})-[:P86_FALLS_WITHIN]->(:E52_TimeSpan {id: $parentTSID}) RETURN childTS")
  List<E52_TimeSpan> findP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);

  @Query("MATCH (timeSpan:E52_TimeSpan {id: $timeSpanId}) SET timeSpan.type = $newType, timeSpan.year = $newYear, timeSpan.month = $newMonth, timeSpan.day = $newDay")
  E52_TimeSpan updateE52Date(@Param("timeSpanId") String timeSpanId, @Param("newType") String newType, @Param("newYear") String newYear, @Param("newMonth") int newMonth, @Param("newDay") int newDay);
  
  @Query("MATCH (childTS:E52_TimeSpan {id: $childTSID}) WITH childTS MATCH (parentTS:E52_TimeSpan {id: $parentTSID}) MERGE (childTS)-[:P86_FALLS_WITHIN]->(parentTS)")
  void createP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);

  @Query("MATCH (:E52_TimeSpan {id: $childTSID})-[r:P86_FALLS_WITHIN]->(:E52_TimeSpan {id: $parentTSID}) DELETE r")
  void deleteP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);
}
  
