package com.kobkbs.graphsvc.fragment;

import java.io.Serializable;
import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

/**
 * SearchResult
 */
public interface SearchResult {

  /**
   * Result
   */
  public class Result implements Serializable{

    public final String id;
    public final String label;
    public final String name;

    Result(String id, String label, String name) {
      this.id = id;
      this.label = label;
      this.name = name;
    }
  }

  @Transactional(value = "transactionManager", readOnly = true)
  Collection<Result> searchEntity(String name);
}
