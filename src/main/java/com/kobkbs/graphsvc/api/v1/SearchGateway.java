package com.kobkbs.graphsvc.api.v1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kobkbs.graphsvc.dto.SearchGateway_DTO;
import com.kobkbs.graphsvc.service.E21_Person_SvcImp;
import com.kobkbs.graphsvc.service.E22_HumanMadeObject_SvcImp;
import com.kobkbs.graphsvc.service.E30_Right_SvcImp;
import com.kobkbs.graphsvc.service.E52_TimeSpan_SvcImp;
import com.kobkbs.graphsvc.service.E53_Place_SvcImp;
import com.kobkbs.graphsvc.service.E5_Event_SvcImp;
import com.kobkbs.graphsvc.service.E74_Group_SvcImp;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class SearchGateway {

  private final E5_Event_SvcImp eventSvc;
  private final E21_Person_SvcImp personSvc;
  private final E22_HumanMadeObject_SvcImp hmoSvc;
  private final E30_Right_SvcImp rightSvc;
  private final E52_TimeSpan_SvcImp timeSpanSvc;
  private final E53_Place_SvcImp placeSvc;
  private final E74_Group_SvcImp groupSvc;

  @GetMapping
  @ResponseBody
  public List<SearchGateway_DTO> getAll(@RequestParam String name) {

    List<SearchGateway_DTO> entityList = new ArrayList<SearchGateway_DTO>(); 

    eventSvc.getE5ByName(name).forEach(entity -> {
      entityList.add(new SearchGateway_DTO("E5 Event", "e5i-event", entity.getId(), entity.getName()));
    });;

    personSvc.getE21ByName(name).forEach(entity -> {
      entityList.add(new SearchGateway_DTO("E21 Person", "e21-person", entity.getId(), entity.getName()));
    });;

    hmoSvc.getE22ByName(name).forEach(entity -> {
      entityList.add(new SearchGateway_DTO("E22 Human Made Object", "e22-hmo", entity.getId(), entity.getName()));
    });;

    rightSvc.getE30ByName(name).forEach(entity -> {
      entityList.add(new SearchGateway_DTO("E30 Right", "e30-right", entity.getId(), entity.getName()));
    });;

    placeSvc.getE53ByName(name).forEach(entity -> {
      entityList.add(new SearchGateway_DTO("E53 Place", "e53-place", entity.getId(), entity.getName()));
    });;

    groupSvc.getE74ByName(name).forEach(entity -> {
      entityList.add(new SearchGateway_DTO("E74 Group", "e74-group", entity.getId(), entity.getName()));
    });;

    return entityList;
  }
}
