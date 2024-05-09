package com.kobkbs.graphsvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E52_TimeSpan;
import com.kobkbs.graphsvc.projection.GetIdAndNameOnly;

public interface E52_TimeSpan_Repo extends Neo4jRepository<E52_TimeSpan, String> {
  @Query("MATCH (n:E52_TimeSpan) return n")
  List<E52_TimeSpan> findAllE52();

  // @Query("MATCH (n:E52_TimeSpan {id: $id}) return n")
  // Optional<E52_TimeSpan> findById(@Param("id") String id);

  @Query("MATCH (n:E52_TimeSpan WHERE toLower(n.name) CONTAINS toLower($name)) RETURN {id: n.id, name: n.name}")
  List<GetIdAndNameOnly> findContainsName(String name);

  @Query("MATCH (n:E52_TimeSpan)-[:P86_FALLS_WITHIN]->(:E52_TimeSpan {name: $fallsWithinTS}) RETURN n")
  List<E52_TimeSpan> findE52ByFallsWithin(@Param("fallsWithinTS") String fallsWithinTS);

  @Query("MATCH (childTS:E52_TimeSpan {id: $childTSID})-[:P86_FALLS_WITHIN]->(:E52_TimeSpan {id: $parentTSID}) RETURN childTS")
  List<E52_TimeSpan> findP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);

  @Query("MATCH (timeSpan:E52_TimeSpan {id: $timeSpanId}) SET timeSpan.name = $newName")
  E52_TimeSpan updateE52Date(@Param("timeSpanId") String timeSpanId, @Param("newName") String newName);
  
  @Query("MATCH (childTS:E52_TimeSpan {id: $childTSID}) WITH childTS MATCH (parentTS:E52_TimeSpan {id: $parentTSID}) MERGE (childTS)-[:P86_FALLS_WITHIN]->(parentTS)")
  void createP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);

  @Query("MATCH (:E52_TimeSpan {id: $childTSID})-[r:P86_FALLS_WITHIN]->(:E52_TimeSpan {id: $parentTSID}) DELETE r")
  void deleteP86(@Param("childTSID") String childTSID, @Param("parentTSID") String parentTSID);
}
  
