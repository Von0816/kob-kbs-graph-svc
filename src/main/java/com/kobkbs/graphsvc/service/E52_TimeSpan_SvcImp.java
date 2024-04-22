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
  public E52_TimeSpan getE52ByDate(LocalDate dateTime) {

    return timeSpanRepo.findE52ByDate(dateTime);
  }

  @Override
  public List<E52_TimeSpan> getE52ByFallsWithin(LocalDate fallsWithinDate) {

    return timeSpanRepo.findE52ByFallsWithin(fallsWithinDate);
  }

  @Override
  public List<E52_TimeSpan> getE52ByYear(String year) {

    return timeSpanRepo.findE52ByYear(year);
  }

  @Override
  public List<E52_TimeSpan> getE52ByMonth(int month) {

    return timeSpanRepo.findE52ByMonth(month);
  }

  @Override
  public void createE52(E52_TimeSpan_DTO timeSpanDTO) {
      E52_TimeSpan timeSpan =  E52_TimeSpan.builder().type(timeSpanDTO.type()).year(timeSpanDTO.year()).build();
      timeSpanRepo.createE52Decade(timeSpan.getId(), timeSpan.getYear());
    // else if(timeSpanDTO.month() < 0 || timeSpanDTO.month() > 12 || timeSpanDTO.day() < 0 || timeSpanDTO.day() > 31) {
    //   throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date.");
    // }
    // else {
    //   timeSpanRepo.save(E52_TimeSpan.builder().type(timeSpanDTO.type()).year(timeSpanDTO.year()).month(timeSpanDTO.month()).day(timeSpanDTO.day()).build());
    // }
  }

  @Override
  public void updateE52Date(String timeSpanId, E52_TimeSpan_DTO timeSpanDTO) {

    if(timeSpanRepo.findById(timeSpanId).isPresent()) {
      if(timeSpanDTO.month() < 1 || timeSpanDTO.month() > 12 || timeSpanDTO.day() < 1 || timeSpanDTO.day() > 31) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid date.");
      }
      else {
      timeSpanRepo.updateE52Date(timeSpanId, timeSpanDTO.type(), timeSpanDTO.year(), timeSpanDTO.month(), timeSpanDTO.day());
      }
    }
    else {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Time-span does not exist.");
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
    timeSpanRepo.delete(timeSpanRepo.findById(timeSpanId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Time Span Does Not Exist")));
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
