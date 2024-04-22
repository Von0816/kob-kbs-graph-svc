package com.kobkbs.graphsvc.api;

import com.kobkbs.graphsvc.model.E5_Event;
import com.kobkbs.graphsvc.service.E5_Event_SvcImp;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/e5_event")
@RequiredArgsConstructor
public class E5_Event_API {
  
  private final E5_Event_SvcImp eventSvc;

  @GetMapping
  public List<E5_Event> GetAllE5Event() {

      return eventSvc.getAllE5();
  }

  @GetMapping("/id/{event_id}")
  public Optional<E5_Event> GetE5EventById(@PathVariable String eventId) {

    return eventSvc.getE5ById(eventId);
  }

  @GetMapping("/name/{eventName}")
  public E5_Event GetE5ByName(@PathVariable String eventName) {

    return eventSvc.getE5ByName(eventName);
  }

  @GetMapping("/p7/{placeName}")
  public List<E5_Event> GetE5ByLocationName(@PathVariable String placeName) {

    return eventSvc.getE5ByLocationName(placeName);
  }

  @GetMapping("/p11/{participantName}")
  public List<E5_Event> GetE5ByParticipantPersonName(@PathVariable String participantName) {

    List<E5_Event> newList = Stream.concat(eventSvc.getE5ByParticipantPersonName(participantName).stream(), eventSvc.getE5ByParticipantGroupName(participantName).stream()).toList();

    return newList;
  }

  @GetMapping("/p4/{date}")
  public List<E5_Event> GetE5ByDate(@PathVariable LocalDate date) {

    return eventSvc.getE5ByTimeSpan(date);
  }

  @PostMapping
  public ResponseEntity<String> CreateE5Event(@RequestBody String eventName) {

    eventSvc.createE5(eventName);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{eventId}/p7/{placeId}")
  public ResponseEntity<String> CreateP7(@PathVariable String eventId, @PathVariable String placeId) {
    
    eventSvc.createP7(eventId, placeId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{eventId}/p11p/{personId}")
  public ResponseEntity<String> CreateP11P(@PathVariable String eventId, @PathVariable String personId) {

    eventSvc.createP11P(eventId, personId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{eventId}/p11g/{groupId}")
  public ResponseEntity<String> CreateP11G(@PathVariable String eventId, @PathVariable String groupId) {

    eventSvc.createP11G(eventId, groupId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{eventId}/p4/{timeSpanId}")
  public ResponseEntity<String> CreateP4(@PathVariable String eventId, @PathVariable String timeSpanId) {

    eventSvc.createP4(eventId, timeSpanId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{event_id}")
  public ResponseEntity<String> UpdateE5Event(@PathVariable String eventId, @RequestBody String newName) {

    eventSvc.updateE5Name(eventId, newName);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{event_id}")
  public ResponseEntity<String> DeleteE5Event(@PathVariable String eventId) {

    eventSvc.deleteE5ById(eventId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{eventId}/p7/{placeId}")
  public ResponseEntity<String> DeleteP7(@PathVariable String eventId, @PathVariable String placeId) {
    
    eventSvc.deleteP7(eventId, placeId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{eventId}/p11p/{personId}")
  public ResponseEntity<String> DeleteP11P(@PathVariable String eventId, @PathVariable String personId) {
    
    eventSvc.deleteP7(eventId, personId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{eventId}/p11g/{groupId}")
  public ResponseEntity<String> DeleteP11G(@PathVariable String eventId, @PathVariable String groupId) {
    
    eventSvc.deleteP7(eventId, groupId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{eventId}/p4/{timeSpanId}")
  public ResponseEntity<String> DeleteP4(@PathVariable String eventId, @PathVariable String timeSpanId) {

    eventSvc.deleteP4(eventId, timeSpanId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
