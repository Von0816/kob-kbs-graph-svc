package com.kobkbs.graphsvc.repository;


import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E30_Right;

public interface E30_Right_Repo extends Neo4jRepository<E30_Right, String> {

  // @Query("MATCH (n:E30_Right {id: $id}) RETURN n")
  // Optional<E30_Right> findById(@Param("id") String id);

  @Query("MATCH (right:E21_Right {id: $rightId}) SET right.name = $newName")
  void updateE21Name(@Param("rightId") String rightId, @Param("newName") String newName);
}
