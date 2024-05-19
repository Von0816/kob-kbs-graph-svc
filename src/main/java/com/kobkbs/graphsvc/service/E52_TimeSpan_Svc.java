package com.kobkbs.graphsvc.service;

import java.util.Optional;
import java.util.List;

import com.kobkbs.graphsvc.model.E52_TimeSpan;

public interface E52_TimeSpan_Svc {

  List<E52_TimeSpan> getAllE52();
  Optional<E52_TimeSpan> getE52ById(String timeSpanId);
  void createE52(String timeSpanName);
  void createP86(String childTSID, String parentTSID);
  void updateE52Date(String timeSpanId, String newName);
  void deleteE52ById(String timeSpanId);
  void deleteP86(String childTSID, String parentTSID);
}
