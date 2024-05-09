package com.kobkbs.graphsvc.dto;

import java.io.Serializable;
import java.util.Set;

import com.kobkbs.graphsvc.model.CIDOC;
import com.kobkbs.graphsvc.model.E21_Person;
import com.kobkbs.graphsvc.model.E30_Right;
import com.kobkbs.graphsvc.model.E53_Place;

public record E21_Person_DTO(
  String id,
  CIDOC label,
  String name,
  Set<E21_Person> parent,
  Set<E30_Right> right,
  Set<E53_Place> residence
) implements Serializable{
}
