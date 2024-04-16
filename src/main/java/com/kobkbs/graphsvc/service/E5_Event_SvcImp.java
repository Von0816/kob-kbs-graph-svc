package com.kobkbs.graphsvc.service;

import com.kobkbs.graphsvc.model.E53_Place;
import com.kobkbs.graphsvc.model.E5_Event;
import com.kobkbs.graphsvc.model.E74_Group;
import com.kobkbs.graphsvc.repository.E21_Person_Repo;
import com.kobkbs.graphsvc.repository.E53_Place_Repo;
import com.kobkbs.graphsvc.repository.E5_Event_Repo;
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
public class E5_Event_SvcImp implements E5_Event_Svc{

  private final E5_Event_Repo eventRepo;
  private final E53_Place_Repo placeRepo;
  private final E21_Person_Repo personRepo;
  private final E74_Group_Repo groupRepo;

  @Override
  public List<E5_Event> getAllE5() {

    return eventRepo.findAll();
  }

  @Override
  public List<E5_Event> getE5ByLocationName(String placeName) {

    return eventRepo.findByLocationName(placeName);
  }

  @Override
  public List<E5_Event> getE5ByParticipantPersonName(String personName) {

    return eventRepo.findByParticipantPersonName(personName);
  }

  @Override
  public List<E5_Event> getE5ByParticipantGroupName(String groupName) {

    return eventRepo.findByParticipantGroupName(groupName);
  }

  @Override
  public E5_Event getE5ByName(String eventName) {

    return eventRepo.findByName(eventName);
  }

  @Override
  public Optional<E5_Event> getE5ById(String eventId) {

    return eventRepo.findById(eventId);
  }

  @Override
  public void createE5(String eventName) {

    eventRepo.save(E5_Event.builder().name(eventName).build());
  }

  @Override
  public void createP7(String eventId, String placeId) {

    if(eventRepo.findById(eventId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Does Not Exist");
    }
    else if(placeRepo.findById(placeId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Place Does Not Exist");
    }
    else {
      eventRepo.createP7(eventId, placeId);
    }
  }

  @Override
  public void createP11P(String eventId, String personId) {

    if(eventRepo.findById(eventId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Does Not Exist");
    }
    else if(personRepo.findById(personId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person Does Not Exist");
    }
    else {
      eventRepo.createP11P(eventId, personId);
    }
  }

  @Override
  public void createP11G(String eventId, String groupId) {

    if(eventRepo.findById(eventId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Does Not Exist");
    }
    else if(groupRepo.findById(groupId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Group Does Not Exist");
    }
    else {
      eventRepo.createP11G(eventId, groupId);
    }
  }

  @Override
  public void updateE5Name(String eventId, String newName) {

    eventRepo.updateE5Name(eventId, newName);
  }


  @Override
  public void deleteE5ById(String eventId) {
    eventRepo.delete(eventRepo.findById(eventId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event Does Not Exist")));
  }

  @Override
  public void deleteP7(String eventId, String placeId) {

    if(eventRepo.findP7(eventId, placeId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      eventRepo.deleteP7(eventId, placeId);
    }
  }

  @Override
  public void deleteP11P(String eventId, String personId) {

    if(eventRepo.findP11P(eventId, personId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      eventRepo.deleteP11P(eventId, personId);
    }
  }

  @Override
  public void deleteP11G(String eventId, String groupId) {

    if(eventRepo.findP11G(eventId, groupId).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      eventRepo.deleteP11G(eventId, groupId);
    }
  }
}
