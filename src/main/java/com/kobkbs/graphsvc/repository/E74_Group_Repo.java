package com.kobkbs.graphsvc.repository;

import java.util.List;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import com.kobkbs.graphsvc.model.E74_Group;

public interface E74_Group_Repo extends Neo4jRepository<E74_Group, String> {
  List<E74_Group> findByName(String groupName);
  List<E74_Group> findByMemberName(String memberName);

  @Query("MATCH (group:E74_Group {id: $groupId})-[:P107_HAS_CURRENT_OR_FORMER_MEMBER]->(:E21_Person {id: $personId}) RETURN group")
  List<E74_Group> findP107(@Param("groupId") String groupId, @Param("personId") String personId);

  @Query("MATCH (group:E74_Group {id: $groupId}) WITH group MATCH (person:E21_Person {id: $personId}) MERGE (group)-[:P107_HAS_CURRENT_OR_FORMER_MEMBER]->(person)")
  void createP107(@Param("groupId") String groupId, @Param("personId") String personId);

  @Query("MATCH (group:E74_Group {id: $groupId}) SET group.name = $newName")
  void updateE74Name(@Param("groupId") String groupId, @Param("newName") String newName);

  @Query("MATCH (:E74_Group {id: $groupId})-[r:P107_HAS_CURRENT_OR_FORMER_MEMBER]->(:E21_Person {id: $personId}) DELETE r")
  void deleteP107(@Param("groupId") String groupId, @Param("personId") String personId);

}
