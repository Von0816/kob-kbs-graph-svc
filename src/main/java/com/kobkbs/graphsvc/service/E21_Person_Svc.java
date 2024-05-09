package com.kobkbs.graphsvc.service;

import java.util.List;
import java.util.Optional;

import com.kobkbs.graphsvc.model.E21_Person;
import com.kobkbs.graphsvc.projection.GetIdAndNameOnly;

public interface E21_Person_Svc {

  List<E21_Person> getAllE21();
  List<E21_Person> getE21ByParentName(String parentName);
  List<E21_Person> getE21ByRightName(String rightName);
  List<E21_Person> getE21ByResidenceName(String residenceName);
  List<GetIdAndNameOnly> searchE21(String personName);
  Optional<E21_Person> getE21ById(String personId);
  void createE21(String personName);
  void createP152(String personId, String parentId);
  void createP74(String personId, String placeId);
  void createP30(String personId, String rightId);
  void updateE21Name(String personId, String newName);
  void deleteE21ById(String personId);
  void deleteP152(String personId, String parentId);
  void deleteP74(String personId, String placeId);
  void deleteP30(String personId, String rightId);
}
