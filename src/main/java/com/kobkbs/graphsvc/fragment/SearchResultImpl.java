package com.kobkbs.graphsvc.fragment;

import java.util.Collection;


import org.springframework.data.neo4j.core.Neo4jClient;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * SearchResultImpl
 */
@Service
@Slf4j
public class SearchResultImpl implements SearchResult{

  private final Neo4jClient neo4jClient;

  SearchResultImpl(Neo4jClient neo4jClient) {
    this.neo4jClient = neo4jClient;
  }

  @Override
  public Collection<Result> searchEntity(String name) {
    return this.neo4jClient
      .query(""
              + "MATCH (n) WHERE toLower(n.name) CONTAINS toLower($name) " 
              + "RETURN n.id as id, "
              + "labels(n)[0] as label, "
              + "n.name as name "
              + "UNION "
              + "MATCH (n)--(m:!E52_TimeSpan) WHERE toLower(n.name) CONTAINS toLower($name) "
              + "RETURN m.id as id, "
              + "labels(m)[0] as label, "
              + "m.name as name"
    )   
    .bind(name).to("name")
    .fetchAs(Result.class)
    .mappedBy((typeSystem, record) -> new Result(record.get("id").asString(), record.get("label").asString(), record.get("name").asString())).all();
  }
}
