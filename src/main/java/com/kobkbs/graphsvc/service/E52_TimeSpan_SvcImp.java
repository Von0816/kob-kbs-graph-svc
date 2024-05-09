package com.kobkbs.graphsvc.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kobkbs.graphsvc.dto.E52_TimeSpan_DTO;
import com.kobkbs.graphsvc.model.E52_TimeSpan;
import com.kobkbs.graphsvc.projection.GetIdAndNameOnly;
import com.kobkbs.graphsvc.repository.E52_TimeSpan_Repo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class E52_TimeSpan_SvcImp implements E52_TimeSpan_Svc{

  private final E52_TimeSpan_Repo timeSpanRepo;

  @Override
  public List<E52_TimeSpan> getAllE52() {

    return timeSpanRepo.findAllE52();
  }

  @Override
  public Optional<E52_TimeSpan> getE52ById(String timeSpanId) {

    return timeSpanRepo.findById(timeSpanId);
  }

  @Override
  public List<GetIdAndNameOnly> searchE52(String tsName) {

    return timeSpanRepo.findContainsName(tsName);
  }

  @Override
  public void createE52(String timeSpanName) {
      timeSpanRepo.save(E52_TimeSpan.builder().name(timeSpanName).build());
  }

  @Override
  public void updateE52Date(String timeSpanId, String newName) {

    if(timeSpanRepo.existsById(timeSpanId)) {
      timeSpanRepo.updateE52Date(timeSpanId, newName);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E52 Time-span with id: " + timeSpanId + " does not exist.");
    }
  }

  @Override
  public void createP86(String childTSID, String parentTSID) {

    if(timeSpanRepo.findById(childTSID).isEmpty() || timeSpanRepo.findById(parentTSID).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time-span does not exist.");
    }
    else {
      timeSpanRepo.createP86(childTSID, parentTSID);
    }
  }

  @Override
  public void deleteE52ById(String timeSpanId) {
    
    if(timeSpanRepo.existsById(timeSpanId)) {
      timeSpanRepo.deleteById(timeSpanId);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "E52 Time-span with id: " + timeSpanId + " does not exist.");
    }
  }

  @Override
  public void deleteP86(String childTSID, String parentTSID) {

    if(timeSpanRepo.findP86(childTSID, parentTSID).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship not found");
    }
    else {
      timeSpanRepo.deleteP86(childTSID, parentTSID);
    }
  }
}
