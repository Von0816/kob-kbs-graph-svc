package com.kobkbs.graphsvc.api;

import java.time.LocalDateTime;
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

import com.kobkbs.graphsvc.model.E52_TimeSpan;
import com.kobkbs.graphsvc.service.E52_TimeSpan_SvcImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/e52_time_span")
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

  @GetMapping("/date_time/{dateTime}")
  public E52_TimeSpan GetE52ByDateTime(@PathVariable LocalDateTime dateTime) {

    return timeSpanSvc.getE52ByDateTime(dateTime);
  }

  @GetMapping("/p86/{fallsWithinDT}")
  public List<E52_TimeSpan> GetE52ByFallsWithin(@PathVariable LocalDateTime fallsWithinDT) {

    return timeSpanSvc.getE52ByFallsWithin(fallsWithinDT);
  }

  @PostMapping
  public ResponseEntity<String> CreateE52(@RequestBody LocalDateTime dateTime) {

    timeSpanSvc.createE52(dateTime);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{childTSID}/p86/{parentTSID}")
  public ResponseEntity<String> CreateP86(@PathVariable String childTSID, @PathVariable String parentTSID) {

    timeSpanSvc.createP86(childTSID, parentTSID);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{timeSpanId}")
  public ResponseEntity<String> UpdateE52DateTime(@PathVariable String timeSpanId, @RequestBody LocalDateTime newDateTime) {

    timeSpanSvc.updateE52DateTime(timeSpanId, newDateTime);

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
