package com.kobkbs.graphsvc.dto;

import java.io.Serializable;
import java.util.Set;

import com.kobkbs.graphsvc.model.E52_TimeSpan;

public record E52_TimeSpan_DTO(
  String id,
  String label,
  String name,
  Set<E52_TimeSpan> timeSpan
    ) implements Serializable{
}
