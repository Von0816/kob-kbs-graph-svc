package com.kobkbs.graphsvc.service;

import java.util.List;
import java.util.Optional;

import com.kobkbs.graphsvc.model.E30_Right;
import com.kobkbs.graphsvc.projection.GetIdAndNameOnly;
import com.kobkbs.graphsvc.repository.E30_Right_Repo;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class E30_Right_SvcImp implements E30_Right_Svc {

  private final E30_Right_Repo rightRepo;

  @Override
  public List<E30_Right> getAllE30() {

    return rightRepo.findAll();
  }

  @Override
  public List<E30_Right> getE30ByName(String name) {
    return rightRepo.findByName(name);
  }

  @Override
  public Optional<E30_Right> getE30ById(String rightId) {

    return rightRepo.findById(rightId);
  }

  @Override
  public List<GetIdAndNameOnly> getE30ContainsName(String rightName) {

    return rightRepo.findContainsName(rightName);
  }

  @Override
  public void createE30(String rightName) {

    rightRepo.save(E30_Right.builder().name(rightName).build());
  }

  @Override
  public void updateE30Name(String rightId, String newName) {

    if(rightRepo.existsById(rightId)) {
      rightRepo.updateE21Name(rightId, newName);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E30 Right with id: " + rightId + " does not exist.");
    }
  }

  @Override
  public void deleteE30ById(String rightId) {

    if(rightRepo.existsById(rightId)) {
      rightRepo.deleteById(rightId);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E30 Right with id: " + rightId + " does not exist.");
    }
  }
}
