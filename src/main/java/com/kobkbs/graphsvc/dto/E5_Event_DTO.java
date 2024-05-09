package com.kobkbs.graphsvc.dto;

import java.io.Serializable;
import java.util.Set;

import com.kobkbs.graphsvc.model.E21_Person;
import com.kobkbs.graphsvc.model.E52_TimeSpan;
import com.kobkbs.graphsvc.model.E53_Place;
import com.kobkbs.graphsvc.model.E74_Group;

public record E5_Event_DTO(
  String id,
  String label,
  String name,
  Set<E53_Place> location,
  Set<E52_TimeSpan> timeSpan,
  Set<E21_Person> participantPerson,
  Set<E74_Group> participantGroup
) implements Serializable{
}
