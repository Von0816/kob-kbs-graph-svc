package com.kobkbs.graphsvc.controller.v1;

import com.kobkbs.graphsvc.model.E74_Group;
import com.kobkbs.graphsvc.service.E74_Group_SvcImp;

import lombok.RequiredArgsConstructor;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com")
@RestController
@RequestMapping("/v1/e74-group")
@RequiredArgsConstructor
public class E74_Group_API implements Serializable{
  
  private final E74_Group_SvcImp groupSvc;

  @GetMapping
  public List<E74_Group> GetAllE74() {

    return groupSvc.getAllE74();
  }

  @GetMapping("/p107/{memberName}")
  public List<E74_Group> GetE74ByMemberName(@PathVariable String memberName) {

    return groupSvc.getE74ByMemberName(memberName);
  }

  @Cacheable(value = "groupCache")
  @GetMapping("/id/{groupId}")
  public Optional<E74_Group> GetE74GroupById(@PathVariable String groupId) {

    return groupSvc.getE74ById(groupId);
  }

  @PostMapping
  public ResponseEntity<String> CreateE74(@RequestBody String groupName) {

    groupSvc.createE74(groupName);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{groupId}/p107/{personId}")
  public ResponseEntity<String> CreateP107(@PathVariable String groupId, @PathVariable String personId) {

    groupSvc.createP107(groupId, personId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @CachePut(key = "#groupId")
  @PutMapping("/{groupId}")
  public ResponseEntity<String> UpdateE74(@PathVariable String groupId, @RequestBody String newName) {

    groupSvc.updateE74Name(groupId, newName);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @CacheEvict(key = "#groupId")
  @DeleteMapping("/{groupId}")
  public ResponseEntity<String> DeleteE74Group(@PathVariable String groupId) {

    groupSvc.deleteE74ById(groupId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{groupId}/p107/{personId}")
  public ResponseEntity<String> DeleteP107(@PathVariable String groupId, @PathVariable String personId) {

    groupSvc.deleteP107(groupId, personId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
