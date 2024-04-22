package com.kobkbs.graphsvc.service;

import java.util.Optional;
import java.util.List;
import java.time.LocalDate;

import com.kobkbs.graphsvc.dto.E52_TimeSpan_DTO;
import com.kobkbs.graphsvc.model.E52_TimeSpan;

public interface E52_TimeSpan_Svc {

  List<E52_TimeSpan> getAllE52();
  List<E52_TimeSpan> getE52ByFallsWithin(LocalDate fallsWithinDT);
  List<E52_TimeSpan> getE52ByYear(String year);
  List<E52_TimeSpan> getE52ByMonth(int month);
  E52_TimeSpan getE52ByDate(LocalDate dateTime);
  Optional<E52_TimeSpan> getE52ById(String timeSpanId);
  void createE52(E52_TimeSpan_DTO timeSpanDTO);
  void createP86(String childTSID, String parentTSID);
  void updateE52Date(String timeSpanId, E52_TimeSpan_DTO timeSpanDTO);
  void deleteE52ById(String timeSpanId);
  void deleteP86(String childTSID, String parentTSID);
}
