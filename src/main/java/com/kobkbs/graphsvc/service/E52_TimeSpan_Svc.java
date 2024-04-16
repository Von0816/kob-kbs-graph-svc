package com.kobkbs.graphsvc.service;

import java.util.Optional;
import java.util.List;
import java.time.LocalDateTime;

import com.kobkbs.graphsvc.model.E52_TimeSpan;

public interface E52_TimeSpan_Svc {

  List<E52_TimeSpan> getAllE52();
  List<E52_TimeSpan> getE52ByFallsWithin(LocalDateTime fallsWithinDT);
  E52_TimeSpan getE52ByDateTime(LocalDateTime dateTime);
  Optional<E52_TimeSpan> getE52ById(String timeSpanId);
  void createE52(LocalDateTime dateTime);
  void createP86(String childTSID, String parentTSID);
  void updateE52DateTime(String timeSpanId, LocalDateTime newDateTime);
  void deleteE52ById(String timeSpanId);
  void deleteP86(String childTSID, String parentTSID);
}
