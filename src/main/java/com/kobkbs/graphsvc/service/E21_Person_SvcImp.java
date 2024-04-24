package com.kobkbs.graphsvc.service;

import com.kobkbs.graphsvc.model.E21_Person;
import com.kobkbs.graphsvc.repository.E21_Person_Repo;
import com.kobkbs.graphsvc.repository.E30_Right_Repo;
import com.kobkbs.graphsvc.repository.E53_Place_Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class E21_Person_SvcImp implements E21_Person_Svc{

  private final E21_Person_Repo personRepo;
  private final E53_Place_Repo placeRepo;
  private final E30_Right_Repo rightRepo;

  @Override
  public List<E21_Person> getAllE21() {
    return personRepo.findAll();
  }

  @Override
  public List<E21_Person> getE21ByParentName(String parentName) {

    return personRepo.findByParentName(parentName);
  }

  @Override
  public List<E21_Person> getE21ByResidenceName(String residenceName) {

    return personRepo.findByResidenceName(residenceName);
  }

  @Override
  public List<E21_Person> getE21ByRightName(String rightName) {

    return personRepo.findByRightName(rightName);
  }

  @Override
  public Optional<E21_Person> getE21ById(String personId) {

    return personRepo.findById(personId);
  }

  @Override
  public List<E21_Person> getE21ByName(String personName) {

    return personRepo.findByName(personName);
  }

  @Override
  public void createE21(String personName) {
    personRepo.save(E21_Person.builder().name(personName).build());
  }

  @Override
  public void createP152(String personId, String parentId) {
    if(personRepo.findById(personId).isEmpty() || personRepo.findById(parentId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
    }
    else {
      personRepo.createP152(personId, parentId);
    }
  }

  @Override
  public void createP74(String personId, String placeId) {
    if(personRepo.findById(personId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
    }
    else if(placeRepo.findById(placeId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place Not Found");
    }
    else {
      personRepo.createP74(personId, placeId);
    }
  }

  @Override
  public void createP30(String personId, String rightId) {
    if(personRepo.findById(personId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
    }
    else if(rightRepo.findById(rightId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Right Not Found");
    }
    else {
      personRepo.createP30(personId, rightId);
    }
  }

  @Override
  public void updateE21Name(String personId, String newName) {
    if(personRepo.findById(personId).isPresent()) {
      personRepo.updateE21Name(personId, newName);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Does Not Exist");
    }
  }

  @Override
  public void deleteE21ById(String personId) {
    personRepo.delete(personRepo.findById(personId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Does Not Exist")));
  }

  @Override
  public void deleteP152(String personId, String parentId) {
    if(personRepo.findP152(personId, parentId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      personRepo.deleteP152(personId, parentId);
    }
  }

  @Override
  public void deleteP74(String personId, String placeId) {
    if(personRepo.findP74(personId, placeId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      personRepo.deleteP74(personId, placeId);
    }
  }

  @Override
  public void deleteP30(String personId, String rightId) {
    if(personRepo.findP30(personId, rightId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      personRepo.deleteP30(personId, rightId);
    }
  }
}
