package com.kobkbs.graphsvc.service;

import com.kobkbs.graphsvc.model.E74_Group;
import com.kobkbs.graphsvc.repository.E21_Person_Repo;
import com.kobkbs.graphsvc.repository.E74_Group_Repo;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class E74_Group_SvcImp implements E74_Group_Svc{

  private final E74_Group_Repo groupRepo;
  private final E21_Person_Repo personRepo;

  @Override
  public List<E74_Group> getAllE74() {

    return groupRepo.findAll();
  }

  @Override
  public List<E74_Group> getE74ByMemberName(String memberName) {

    return groupRepo.findByMemberName(memberName);
  }

  @Override
  public Optional<E74_Group> getE74ById(String groupId) {

    return groupRepo.findById(groupId);
  }

  @Override
  public List<E74_Group> getE74ByName(String groupName) {

    return groupRepo.findByNameContainsIgnoreCase(groupName);
  }

  @Override
  public void createE74(String groupName) {

    groupRepo.save(E74_Group.builder().name(groupName).build());
  }

  @Override
  public void createP107(String groupId, String personId) {

    if(groupRepo.findById(groupId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Not Found");
    }
    else if(personRepo.findById(personId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
    }
    else {
      groupRepo.createP107(groupId, personId);
    }
  }

  @Override
  public void updateE74Name(String groupId, String newName) {

    if(groupRepo.existsById(groupId)) {
      groupRepo.updateE74Name(groupId, newName);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E74 Group with id: " + groupId + " does not exist.");
    }
  }

  @Override
  public void deleteE74ById(String groupId) {

    if(groupRepo.existsById(groupId)) {
      groupRepo.deleteById(groupId);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E74 Group with id: " + groupId + " does not exist.");
    }
  }

  @Override
  public void deleteP107(String groupId, String personId) {

    if(groupRepo.findP107(groupId, personId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      groupRepo.deleteP107(groupId, personId);
    }
  }
}
