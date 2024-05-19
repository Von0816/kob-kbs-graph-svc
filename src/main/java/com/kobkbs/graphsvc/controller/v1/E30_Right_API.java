package com.kobkbs.graphsvc.controller.v1;

import com.kobkbs.graphsvc.model.E30_Right;
import com.kobkbs.graphsvc.service.E30_Right_Svc;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com")
@CacheConfig(cacheNames = "rightCache")
@RestController
@RequestMapping("/api/v1/e30-right")
@RequiredArgsConstructor
public class E30_Right_API {
  
  private final E30_Right_Svc rightSvc;

  @GetMapping
  public List<E30_Right> GetAllE30() {
    return rightSvc.getAllE30();
  }

  @Cacheable(key = "#rightId")
  @GetMapping("/id/{rightId}")
  public Optional<E30_Right> GetE30RightById(@PathVariable String rightId) {

    return rightSvc.getE30ById(rightId);
  }

  @PostMapping
  public ResponseEntity<String> CreateE30(@RequestBody String rightName) {

    rightSvc.createE30(rightName);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @CachePut(key = "#rightId")
  @PutMapping("/{rightId}")
  public ResponseEntity<String> UpdateE30Name(@PathVariable String rightId, @RequestBody String newName) {

    rightSvc.updateE30Name(rightId, newName);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @CacheEvict(key = "#rightId")
  @DeleteMapping("/{rightId}")
  public ResponseEntity<String> DeleteE30Right(@PathVariable String rightId) {

    rightSvc.deleteE30ById(rightId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
