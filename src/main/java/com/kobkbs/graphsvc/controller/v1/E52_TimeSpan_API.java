package com.kobkbs.graphsvc.controller.v1;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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

import com.kobkbs.graphsvc.model.E52_TimeSpan;
import com.kobkbs.graphsvc.service.E52_TimeSpan_SvcImp;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com")
@CacheConfig(cacheNames = "tsCache")
@RestController
@RequestMapping("/api/v1/e52-time-span")
@RequiredArgsConstructor
public class E52_TimeSpan_API {

  private final E52_TimeSpan_SvcImp timeSpanSvc;

  @GetMapping
  public List<E52_TimeSpan> GetAllE21() {

    return timeSpanSvc.getAllE52();
  } 

  @Cacheable(key = "#timeSpanId")
  @GetMapping("/id/{timeSpanId}")
  public Optional<E52_TimeSpan> GetE52ById(@PathVariable String timeSpanId) {

    return timeSpanSvc.getE52ById(timeSpanId);
  }

  @PostMapping
  public ResponseEntity<String> CreateE52(@RequestBody String timeSpanName) {

    timeSpanSvc.createE52(timeSpanName);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{childTSID}/p86/{parentTSID}")
  public ResponseEntity<String> CreateP86(@PathVariable String childTSID, @PathVariable String parentTSID) {

    timeSpanSvc.createP86(childTSID, parentTSID);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @CachePut(key = "#timeSpanId")
  @PutMapping("/{timeSpanId}")
  public ResponseEntity<String> UpdateE52Date(@PathVariable String timeSpanId, @RequestBody String newName) {

    timeSpanSvc.updateE52Date(timeSpanId, newName);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @CacheEvict(key = "#timeSpanId")
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
