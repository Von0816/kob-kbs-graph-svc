package com.kobkbs.graphsvc.dto;

import java.io.Serializable;
import java.util.Set;

import com.kobkbs.graphsvc.model.E53_Place;

public record E53_Place_DTO(
  String id,
  String label,
  String name,
  Set<E53_Place> place
) implements Serializable{
}
