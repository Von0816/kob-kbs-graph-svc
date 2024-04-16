package com.kobkbs.graphsvc.service;

import java.util.List;
import java.util.Optional;

import com.kobkbs.graphsvc.model.E22_HumanMadeObject;

public interface E22_HumanMadeObject_Svc {

  List<E22_HumanMadeObject> getAllE22();
  List<E22_HumanMadeObject> getE22ByCurrPermaLocName(String currPermaLocName);
  List<E22_HumanMadeObject> getE22ByCurrLocName(String currLocName);
  List<E22_HumanMadeObject> getE22ByOwnerPersonName(String ownerPersonName);
  List<E22_HumanMadeObject> getE22ByOwnerGroupName(String ownerGroupName);
  Optional<E22_HumanMadeObject> getE22ById(String hmoId);
  E22_HumanMadeObject getE22ByName(String hmoName);
  void createE22(String hmoName);
  void updateE22Name(String hmoId, String newName);
  void createP54(String hmoId, String placeId);
  void createP55(String hmoId, String placeId);
  void createP51P(String hmoId, String personId);
  void createP51G(String hmoId, String groupId);
  void deleteE22(String hmoId);
  void deleteP54(String hmoId, String placeId);
  void deleteP55(String hmoId, String placeId);
  void deleteP51P(String hmoId, String personId);
  void deleteP51G(String hmoId, String groupId);
}
