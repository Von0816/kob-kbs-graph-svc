package com.kobkbs.graphsvc.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kobkbs.graphsvc.model.E52_TimeSpan;
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

    return timeSpanRepo.findAll();
  }

  @Override
  public Optional<E52_TimeSpan> getE52ById(String timeSpanId) {

    return timeSpanRepo.findById(timeSpanId);
  }

  @Override
  public E52_TimeSpan getE52ByDateTime(LocalDateTime dateTime) {

    return timeSpanRepo.findE52ByDateTime(dateTime);
  }

  @Override
  public List<E52_TimeSpan> getE52ByFallsWithin(LocalDateTime fallsWithinDT) {

    return timeSpanRepo.findE52ByFallsWithin(fallsWithinDT);
  }

  @Override
  public void createE52(LocalDateTime dateTime) {
    timeSpanRepo.save(E52_TimeSpan.builder().date_time(dateTime).build());
  }

  @Override
  public void updateE52DateTime(String timeSpanId, LocalDateTime newDateTime) {

    if(timeSpanRepo.findById(timeSpanId).isPresent()) {
      timeSpanRepo.updateE52DateTime(timeSpanId, newDateTime);
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time Span Does Not Exist");
    }
  }

  @Override
  public void createP86(String childTSID, String parentTSID) {

    if(timeSpanRepo.findById(childTSID).isEmpty() || timeSpanRepo.findById(parentTSID).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time Span Does Not Exist");
    }
    else {
      timeSpanRepo.createP86(childTSID, parentTSID);
    }
  }

  @Override
  public void deleteE52ById(String timeSpanId) {
    timeSpanRepo.delete(timeSpanRepo.findById(timeSpanId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time Span Does Not Exist")));
  }

  @Override
  public void deleteP86(String childTSID, String parentTSID) {

    if(timeSpanRepo.findP86(childTSID, parentTSID).isEmpty()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Relationship Not Found");
    }
    else {
      timeSpanRepo.deleteP86(childTSID, parentTSID);
    }
  }
}
