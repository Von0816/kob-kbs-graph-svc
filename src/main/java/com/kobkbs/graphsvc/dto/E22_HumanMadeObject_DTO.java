package com.kobkbs.graphsvc.dto;

import java.io.Serializable;
import java.util.Set;

import com.kobkbs.graphsvc.model.E21_Person;
import com.kobkbs.graphsvc.model.E53_Place;
import com.kobkbs.graphsvc.model.E74_Group;

public record E22_HumanMadeObject_DTO(
  String id,
  String label,
  String name,
  String type,
  Set<E53_Place> currentLocation,
  Set<E53_Place> currentPermanentLocation,
  Set<E21_Person> currentOrFormerOwnerPerson,
  Set<E74_Group> currentOrFormerOwnerGroup
    ) implements Serializable{
}
