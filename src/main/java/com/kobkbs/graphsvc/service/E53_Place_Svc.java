package com.kobkbs.graphsvc.service;

import java.util.List;
import java.util.Optional;

import com.kobkbs.graphsvc.model.E53_Place;
import com.kobkbs.graphsvc.projection.GetIdAndNameOnly;

public interface E53_Place_Svc {

  List<E53_Place> getAllE53();
  List<GetIdAndNameOnly> searchE53(String placeName);
  Optional<E53_Place> getE53ById(String placeId);
  void createE53(String placeName);
  void updateE53Name(String placeId, String newName);
  void deleteE53ById(String placeId);
}
