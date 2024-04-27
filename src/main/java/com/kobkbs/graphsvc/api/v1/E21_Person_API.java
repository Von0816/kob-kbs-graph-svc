package com.kobkbs.graphsvc.api.v1;

import com.kobkbs.graphsvc.model.E21_Person;
import com.kobkbs.graphsvc.service.E21_Person_SvcImp;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com/*", maxAge = 600)
@RestController
@RequestMapping("/v1/e21-person")
@RequiredArgsConstructor
public class E21_Person_API {
  
  private final E21_Person_SvcImp personSvc;

  @GetMapping
  public List<E21_Person> GetAllE21() {

      return personSvc.getAllE21();
  }

  @GetMapping("/id/{personId}")
  public Optional<E21_Person> GetE21ById(@PathVariable String personId) {

    return personSvc.getE21ById(personId);
  }

  @GetMapping("/name/{personName}")
  public List<E21_Person> GetE21ByName(@PathVariable String personName) {

    return personSvc.getE21ByName(personName);
  }

  @GetMapping("/p152/{parentName}")
  public List<E21_Person> GetE21ByParentName(@PathVariable String parentName) {

    return personSvc.getE21ByParentName(parentName);
  }

  @GetMapping("/p74/{residenceName}")
  public List<E21_Person>  GetE21ByResidenceName(@PathVariable String residenceName) {

    return personSvc.getE21ByResidenceName(residenceName);
  }

  @GetMapping("/p30/{rightName}")
  public List<E21_Person> GetE21ByRightName(@PathVariable String rightName) {

    return personSvc.getE21ByRightName(rightName);
  }

  @PostMapping
  public ResponseEntity<String> CreateE21(@RequestBody String personName) {
    
    personSvc.createE21(personName);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{personId}")
  public ResponseEntity<String> UpdateE21Name(@PathVariable String personId, @RequestBody String newName) {

    personSvc.updateE21Name(personId, newName);
    
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/{personId}/p152/{parentId}")
  public ResponseEntity<String> createP152(@PathVariable String personId, @PathVariable String parentId) {

    personSvc.createP152(personId, parentId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/{personId}/p74/{placeId}")
  public ResponseEntity<String> createP74(@PathVariable String personId, @PathVariable String placeId) {

    personSvc.createP74(personId, placeId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/{personId}/p30/{rightId}")
  public ResponseEntity<String> createP30(@PathVariable String personId, @PathVariable String rightId) {

    personSvc.createP30(personId, rightId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{personId}")
  public ResponseEntity<String> DeleteE21ById(@PathVariable String personId) {

    personSvc.deleteE21ById(personId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{personId}/p152/{parentId}")
  public ResponseEntity<String> deleteHasParent(@PathVariable String personId, @PathVariable String parentId) {
    personSvc.deleteP152(personId, parentId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{personId}/p74/{parentId}")
  public ResponseEntity<String> deleteP74(@PathVariable String personId, @PathVariable String placeId) {
    personSvc.deleteP74(personId, placeId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{personId}/p30/{parentId}")
  public ResponseEntity<String> deleteP30(@PathVariable String personId, @PathVariable String rightId) {
    personSvc.deleteP30(personId, rightId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}

