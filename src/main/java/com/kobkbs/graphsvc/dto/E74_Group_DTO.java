package com.kobkbs.graphsvc.dto;

import java.io.Serializable;
import java.util.Set;

import com.kobkbs.graphsvc.model.E21_Person;

public record E74_Group_DTO(
  String id,
  String label,
  String name,
  Set<E21_Person> currentOrFormerMember
) implements Serializable{
}
