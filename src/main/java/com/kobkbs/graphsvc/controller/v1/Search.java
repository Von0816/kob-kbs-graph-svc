package com.kobkbs.graphsvc.controller.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kobkbs.graphsvc.dto.Search_DTO;
import com.kobkbs.graphsvc.service.E21_Person_SvcImp;
import com.kobkbs.graphsvc.service.E22_HumanMadeObject_SvcImp;
import com.kobkbs.graphsvc.service.E30_Right_SvcImp;
import com.kobkbs.graphsvc.service.E52_TimeSpan_SvcImp;
// import com.kobkbs.graphsvc.service.E52_TimeSpan_SvcImp;
import com.kobkbs.graphsvc.service.E53_Place_SvcImp;
import com.kobkbs.graphsvc.service.E5_Event_SvcImp;
import com.kobkbs.graphsvc.service.E74_Group_SvcImp;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com")
@CacheConfig(cacheNames = "searchResult")
@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class Search{

  private final E5_Event_SvcImp eventSvc;
  private final E21_Person_SvcImp personSvc;
  private final E22_HumanMadeObject_SvcImp hmoSvc;
  private final E30_Right_SvcImp rightSvc;
  private final E52_TimeSpan_SvcImp timeSpanSvc;
  private final E53_Place_SvcImp placeSvc;
  private final E74_Group_SvcImp groupSvc;

  @Cacheable(key = "#name")
  @GetMapping
  @ResponseBody
  public List<Search_DTO> getAll(@RequestParam String name) {

    List<Search_DTO> entityList = new ArrayList<Search_DTO>(); 

    eventSvc.searchE5(name).forEach(entity -> {
      entityList.add(new Search_DTO("E5 Event", "e5-event", entity.getId(), entity.getName()));
    });;

    personSvc.searchE21(name).forEach(entity -> {
      entityList.add(new Search_DTO("E21 Person", "e21-person", entity.getId(), entity.getName()));
    });;

    hmoSvc.searchE22(name).forEach(entity -> {
      entityList.add(new Search_DTO("E22 Human Made Object", "e22-hmo", entity.getId(), entity.getName()));
    });;

    rightSvc.searchE30(name).forEach(entity -> {
      entityList.add(new Search_DTO("E30 Right", "e30-right", entity.getId(), entity.getName()));
    });;

    timeSpanSvc.searchE52(name).forEach(entity -> {
      entityList.add(new Search_DTO("E52 Time-span", "e52-time-span", entity.getId(), entity.getName()));
    });

    placeSvc.searchE53(name).forEach(entity -> {
      entityList.add(new Search_DTO("E53 Place", "e53-place", entity.getId(), entity.getName()));
    });;

    groupSvc.searchE74(name).forEach(entity -> {
      entityList.add(new Search_DTO("E74 Group", "e74-group", entity.getId(), entity.getName()));
    });;

    return entityList;
  }
}
