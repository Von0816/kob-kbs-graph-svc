package com.kobkbs.graphsvc.service;

import java.util.List;
import java.util.Optional;

import com.kobkbs.graphsvc.model.E30_Right;

public interface E30_Right_Svc {
  
  List<E30_Right> getAllE30();
  Optional<E30_Right> getE30ById(String rightId);
  E30_Right getE30ByName(String rightName);
  void createE30(String rightName);
  void updateE30Name(String rightId, String newName);
  void deleteE30ById(String rightId);
}
