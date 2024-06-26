package com.kobkbs.graphsvc.controller.v1;

import com.kobkbs.graphsvc.model.E53_Place;
import com.kobkbs.graphsvc.service.E53_Place_SvcImp;

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
@CacheConfig(cacheNames = "placeCache")
@RestController
@RequestMapping("/v1/e53-place")
@RequiredArgsConstructor
public class E53_Place_API {
  
  private final E53_Place_SvcImp placeSvc;

  @GetMapping
  public List<E53_Place> GetAllE53() {

    return placeSvc.getAllE53();
  }

  @Cacheable(key = "#placeId")
  @GetMapping("/id/{placeId}")
  public Optional<E53_Place> GetE53PlaceById(@PathVariable String placeId) {

    return placeSvc.getE53ById(placeId);
  }

  @PostMapping
  public ResponseEntity<String> CreateE53Place(@RequestBody String placeName) {

    placeSvc.createE53(placeName);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @CachePut(key = "#placeId")
  @PutMapping("/{placeId}")
  public ResponseEntity<String> UpdateE53Place(@PathVariable String placeId, @RequestBody String newName) {

    placeSvc.updateE53Name(placeId, newName);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @CacheEvict(key = "#placeId")
  @DeleteMapping("/{placeId}")
  public ResponseEntity<String> DeleteE53Place(@PathVariable String placeId) {

    placeSvc.deleteE53ById(placeId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
