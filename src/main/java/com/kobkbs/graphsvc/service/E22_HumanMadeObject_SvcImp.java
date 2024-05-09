package com.kobkbs.graphsvc.service;

import com.kobkbs.graphsvc.dto.E22_HumanMadeObject_DTO;
import com.kobkbs.graphsvc.model.E22_HumanMadeObject;
import com.kobkbs.graphsvc.projection.GetIdAndNameOnly;
import com.kobkbs.graphsvc.repository.E21_Person_Repo;
import com.kobkbs.graphsvc.repository.E22_HumanMadeObject_Repo;
import com.kobkbs.graphsvc.repository.E53_Place_Repo;
import com.kobkbs.graphsvc.repository.E74_Group_Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class E22_HumanMadeObject_SvcImp implements E22_HumanMadeObject_Svc{

  private final E22_HumanMadeObject_Repo humanMadeObjectRepo;
  private final E53_Place_Repo placeRepo;
  private final E21_Person_Repo personRepo;
  private final E74_Group_Repo groupRepo;

  @Override
  public List<E22_HumanMadeObject> getAllE22() {
    return humanMadeObjectRepo.findAll();
  }

  @Override
  public List<E22_HumanMadeObject> getE22ByType(String hmoType) {

    return humanMadeObjectRepo.findByType(hmoType);
  }

  @Override
  public List<E22_HumanMadeObject> getE22ByCurrPermaLocName(String currPermaLocName) {

    return humanMadeObjectRepo.findByCurrPermaLocName(currPermaLocName);
  }

  @Override 
  public List<E22_HumanMadeObject> getE22ByCurrLocName(String currLocName) {

    return humanMadeObjectRepo.findByCurrLocName(currLocName);
  }

  @Override
  public List<E22_HumanMadeObject> getE22ByOwnerPersonName(String ownerPersonName) {

    return humanMadeObjectRepo.findByOwnerPersonName(ownerPersonName);
  }

  @Override 
  public List<E22_HumanMadeObject> getE22ByOwnerGroupName(String ownerGroupName) {

    return humanMadeObjectRepo.findByOwnerGroupName(ownerGroupName);
  }

  @Override
  public Optional<E22_HumanMadeObject> getE22ById(String hmoId) {

    return humanMadeObjectRepo.findById(hmoId);
  }

  @Override
  public List<GetIdAndNameOnly> searchE22(String hmoName) {

    return humanMadeObjectRepo.findContainsName(hmoName);
  }

  @Override
  public void createE22(E22_HumanMadeObject_DTO hmoDTO) {

    humanMadeObjectRepo.save(E22_HumanMadeObject.builder().name(hmoDTO.name()).type(hmoDTO.type()).build());
  }

  @Override
  public void updateE22Name(String hmoId, String newName) {
    if(humanMadeObjectRepo.existsById(hmoId)) {
      humanMadeObjectRepo.updateE22Name(hmoId, newName);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E22 Human Made Object with id: " + hmoId + " Does Not Exist");
    }
  }

  @Override
  public void createP54(String hmoId, String placeId) {

    if(humanMadeObjectRepo.findById(hmoId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Human Made Object Not Found");
    }
    else if(placeRepo.findById(placeId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place Not Found");
    }
    else {
      humanMadeObjectRepo.createP54(hmoId, placeId);
    }
  }

  @Override
  public void createP55(String hmoId, String placeId) {
    if(humanMadeObjectRepo.findById(hmoId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Human Made Object Not Found");
    }
    else if(placeRepo.findById(placeId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place Not Found");
    }
    else {
      humanMadeObjectRepo.createP55(hmoId, placeId);
    }
  }

  @Override
  public void createP51P(String hmoId, String personId) {
    if(humanMadeObjectRepo.findById(hmoId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Human Made Object Not Found");
    }
    else if(personRepo.findById(personId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Not Found");
    }
    else {
      humanMadeObjectRepo.createP51P(hmoId, personId);
    }
  }

  @Override
  public void createP51G(String hmoId, String groupId) {
    if(humanMadeObjectRepo.findById(hmoId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Human Made Object Not Found");
    }
    else if(groupRepo.findById(groupId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Not Found");
    }
    else {
      humanMadeObjectRepo.createP51G(hmoId, groupId);
    }
  }

  @Override
  public void deleteE22(String hmoId) {
    if(humanMadeObjectRepo.existsById(hmoId)) {
      humanMadeObjectRepo.deleteById(hmoId);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E22 Human Made Object with id: " + hmoId + " does not exist.");
    }
  }

  @Override
  public void deleteP54(String hmoId, String placeId) {
    if(humanMadeObjectRepo.findP54(hmoId, placeId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      humanMadeObjectRepo.deleteP54(hmoId, placeId);
    }
  }

  @Override
  public void deleteP55(String hmoId, String placeId) {
    if(humanMadeObjectRepo.findP55(hmoId, placeId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      humanMadeObjectRepo.deleteP55(hmoId, placeId);
    }
  }

  @Override
  public void deleteP51P(String hmoId, String personId) {
    if(humanMadeObjectRepo.findP51P(hmoId, personId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      humanMadeObjectRepo.deleteP51P(hmoId, personId);
    }
  }

  @Override
  public void deleteP51G(String hmoId, String groupId) {
    if(humanMadeObjectRepo.findP51G(hmoId, groupId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      humanMadeObjectRepo.deleteP51G(hmoId, groupId);
    }
  }
}
