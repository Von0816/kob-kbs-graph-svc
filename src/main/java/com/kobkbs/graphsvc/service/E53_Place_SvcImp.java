package com.kobkbs.graphsvc.service;

import com.kobkbs.graphsvc.model.E53_Place;
import com.kobkbs.graphsvc.repository.E53_Place_Repo;

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
public class E53_Place_SvcImp implements E53_Place_Svc{

  private final E53_Place_Repo placeRepo;

  @Override
  public List<E53_Place> getAllE53() {
    
    return placeRepo.findAll();
  }

  @Override
  public Optional<E53_Place> getE53ById(String placeId) {

    return placeRepo.findById(placeId);
  }

  @Override
  public void createE53(String placeName) {

    placeRepo.save(E53_Place.builder().name(placeName).build());
  }

  @Override
  public void updateE53Name(String placeId, String newName) {
  
    if(placeRepo.existsById(placeId)) {
      placeRepo.updateE53Name(placeId, newName);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E53 Place with id: " + placeId + " Does Not Exist");
    }
  }

  @Override
  public void deleteE53ById(String placeId) {
    if(placeRepo.existsById(placeId)) {
      placeRepo.deleteById(placeId);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E53 Place with id " + placeId + " does not exist.");
    }
  }
}
