package com.kobkbs.graphsvc.api.v1;

import com.kobkbs.graphsvc.model.E22_HumanMadeObject;
import com.kobkbs.graphsvc.service.E22_HumanMadeObject_SvcImp;
import com.kobkbs.graphsvc.dto.E22_HumanMadeObject_DTO;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com")
@RestController
@RequestMapping("/v1/e22-hmo")
@RequiredArgsConstructor
public class E22_HumanMadeObject_API {
  
  private final E22_HumanMadeObject_SvcImp hmoSvc;

  @GetMapping
  public List<E22_HumanMadeObject> GetAllE22HMO() {

      return hmoSvc.getAllE22();
  }

  @GetMapping("/id/{hmoId}")
  public Optional<E22_HumanMadeObject> GetE22HMOById(@PathVariable String hmoId) {

    return hmoSvc.getE22ById(hmoId);
  }

  @GetMapping("/name/{hmoName}")
  public List<E22_HumanMadeObject> GetE22ByHMOName(@PathVariable String hmoName) {

    return hmoSvc.getE22ByName(hmoName);
  }

  @GetMapping("/type/{hmoType}")
  public List<E22_HumanMadeObject> GetE22ByType(@PathVariable String hmoType) {

    return hmoSvc.getE22ByType(hmoType);
  }

  @GetMapping("/p54/{placeName}")
  public List<E22_HumanMadeObject> GetE22ByCurrPermaLocName(@PathVariable String placeName) {

    return hmoSvc.getE22ByCurrPermaLocName(placeName);

  }

  @GetMapping("/p55/{placeName}")
  public List<E22_HumanMadeObject> GetE22ByCurrLocName(@PathVariable String placeName) {

    return hmoSvc.getE22ByCurrLocName(placeName);
  }

  @GetMapping("/p51/{ownerName}")
  public List<E22_HumanMadeObject> GetE22ByOwnerPersonName(@PathVariable String ownerName) {

    List<E22_HumanMadeObject> newList = Stream.concat(hmoSvc.getE22ByOwnerPersonName(ownerName).stream(), hmoSvc.getE22ByOwnerGroupName(ownerName).stream()).toList();

    return newList;
  }

  @PostMapping
  public ResponseEntity<String> CreateE22(@RequestBody E22_HumanMadeObject_DTO request) {

    hmoSvc.createE22(request);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PutMapping("/{hmoId}")
  public ResponseEntity<String> UpdateE22Name(@PathVariable String hmoId, @RequestBody String newName) {

    hmoSvc.updateE22Name(hmoId, newName);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/{hmoId}/p54/{placeId}")
  public ResponseEntity<String> createP54(@PathVariable String hmoId, @PathVariable String placeId) {

    hmoSvc.createP54(hmoId, placeId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{hmoId}/p55/{placeId}")
  public ResponseEntity<String> createP55(@PathVariable String hmoId, @PathVariable String placeId) {

    hmoSvc.createP55(hmoId, placeId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{hmoId}/p51p/{personId}")
  public ResponseEntity<String> createP51P(@PathVariable String hmoId, @PathVariable String personId) {

    hmoSvc.createP51P(hmoId, personId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/{hmoId}/p51g/{groupId}")
  public ResponseEntity<String> createP51G(@PathVariable String hmoId, @PathVariable String groupId) {

    hmoSvc.createP51G(hmoId, groupId);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping("/{hmoId}")
  public ResponseEntity<String> DeleteE22(@PathVariable String hmoId) {

    hmoSvc.deleteE22(hmoId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{hmoId}/p54/{placeId}")
  public ResponseEntity<String> deleteP54(@PathVariable String hmoId, @PathVariable String placeId) {

    hmoSvc.deleteP54(hmoId, placeId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{hmoId}/p55/{placeId}")
  public ResponseEntity<String> deleteP55(@PathVariable String hmoId, @PathVariable String placeId) {

    hmoSvc.deleteP55(hmoId, placeId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{hmoId}/p51p/{personId}")
  public ResponseEntity<String> deleteP51P(@PathVariable String hmoId, @PathVariable String personId) {

    hmoSvc.deleteP51P(hmoId, personId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @DeleteMapping("/{hmoId}/p51g/{groupId}")
  public ResponseEntity<String> deleteP51G(@PathVariable String hmoId, @PathVariable String groupId) {

    hmoSvc.deleteP51G(hmoId, groupId);

    return ResponseEntity.status(HttpStatus.OK).build();
  }
}
