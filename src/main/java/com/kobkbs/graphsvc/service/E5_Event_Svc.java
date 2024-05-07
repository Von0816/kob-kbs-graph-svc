package com.kobkbs.graphsvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.kobkbs.graphsvc.model.E5_Event;
import com.kobkbs.graphsvc.projection.GetIdAndNameOnly;

public interface E5_Event_Svc {

  List<E5_Event> getAllE5();
  List<E5_Event> getE5ByName(String name);
  List<E5_Event> getE5ByLocationName(String placeName);
  List<E5_Event> getE5ByParticipantPersonName(String personName);
  List<E5_Event> getE5ByParticipantGroupName(String groupName);
  List<E5_Event> getE5ByTimeSpan(LocalDate date);
  List<GetIdAndNameOnly> getE5ContainsName(String eventName);
  Optional<E5_Event> getE5ById(String eventId);
  void createE5(String eventName);
  void createP7(String eventId, String placeId);
  void createP11P(String eventId, String personId);
  void createP11G(String eventId, String groupId);
  void createP4(String eventId, String timeSpanId);
  void updateE5Name(String eventId, String newName);
  void deleteE5ById(String eventId);
  void deleteP7(String eventId, String placeId);
  void deleteP11P(String eventId, String personId);
  void deleteP11G(String eventId, String groupId);
  void deleteP4(String eventId, String timeSpanId);
}
