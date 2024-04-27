package com.kobkbs.graphsvc.api.v1;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.kobkbs.graphsvc.dto.E52_TimeSpan_DTO;
import com.kobkbs.graphsvc.model.E52_TimeSpan;
import com.kobkbs.graphsvc.service.E52_TimeSpan_SvcImp;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com")
@RestController
@RequestMapping("/v1/e52-time-span")
@RequiredArgsConstructor
public class E52_TimeSpan_API {

  private final E52_TimeSpan_SvcImp timeSpanSvc;

  @GetMapping
  public List<E52_TimeSpan> GetAllE21() {

    return timeSpanSvc.getAllE52();
  } 

  @GetMapping("/id/{timeSpanId}")
  public Optional<E52_TimeSpan> GetE52ById(@PathVariable String timeSpanId) {

    return timeSpanSvc.getE52ById(timeSpanId);
  }

  @GetMapping("/year/{year}")
  public List<E52_TimeSpan> GetE52ByYear(@PathVariable String year) {

    return timeSpanSvc.getE52ByYear(year);
  }

  @GetMapping("/month/{month}")
  public List<E52_TimeSpan> GetE52ByMonth(@PathVariable int month) {

    return timeSpanSvc.getE52ByMonth(month);
  }

  @GetMapping("/datetime/{dateTime}")
  public E52_TimeSpan GetE52ByDate(@PathVariable LocalDate dateTime) {

    return timeSpanSvc.getE52ByDate(dateTime);
  }

  @GetMapping("/p86/{fallsWithinDT}")
  public List<E52_TimeSpan> GetE52ByFallsWithin(@PathVariable LocalDate fallsWithinDT) {

    return timeSpanSvc.getE52ByFallsWithin(fallsWithinDT);
  }

  @PostMapping
  public ResponseEntity<String> CreateE52(@RequestBody E52_TimeSpan_DTO timeSpanDTO) {

    timeSpanSvc.createE52(timeSpanDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{childTSID}/p86/{parentTSID}")
  public ResponseEntity<String> CreateP86(@PathVariable String childTSID, @PathVariable String parentTSID) {

    timeSpanSvc.createP86(childTSID, parentTSID);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{timeSpanId}")
  public ResponseEntity<String> UpdateE52Date(@PathVariable String timeSpanId, @RequestBody E52_TimeSpan_DTO timeSpanDTO) {

    timeSpanSvc.updateE52Date(timeSpanId, timeSpanDTO);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{timeSpanId}")
  public ResponseEntity<String> DeleteE52ById(@PathVariable String timeSpanId) {

    timeSpanSvc.deleteE52ById(timeSpanId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{childTSID}/p86/{parentTSID}")
  public ResponseEntity<String> DeleteP86(@PathVariable String childTSID, @PathVariable String parentTSID) {

    timeSpanSvc.deleteP86(childTSID, parentTSID);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
