package com.kobkbs.graphsvc.repository;

import java.util.List;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E53_Place;
import com.kobkbs.graphsvc.projection.GetIdAndNameOnly;

public interface E53_Place_Repo extends Neo4jRepository<E53_Place, String> {
  List<E53_Place> findByName(String name);
  @Query("MATCH (n:E53_Place WHERE toLower(n.name) CONTAINS toLower($name)) RETURN {id: n.id, name: n.name}")
  List<GetIdAndNameOnly> findContainsName(String name);

  @Query("MATCH (place:E53_Place {id: $placeId}) SET place.name = $newName")
  void updateE53Name(@Param("placeId") String placeId, @Param("newName") String newName);
}
