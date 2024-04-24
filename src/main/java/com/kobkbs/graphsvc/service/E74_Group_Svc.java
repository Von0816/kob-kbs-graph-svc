package com.kobkbs.graphsvc.service;

import java.util.List;
import java.util.Optional;

import com.kobkbs.graphsvc.model.E74_Group;

public interface E74_Group_Svc {

  List<E74_Group> getAllE74();
  List<E74_Group> getE74ByMemberName(String memberName);
  List<E74_Group> getE74ByName(String groupName);
  Optional<E74_Group> getE74ById(String groupId);
  void createE74(String groupName);
  void createP107(String groupId, String personId);
  void updateE74Name(String groupId, String newName);
  void deleteE74ById(String groupId);
  void deleteP107(String groupId, String personId);
}
