package com.kobkbs.graphsvc.controller.v1;

import java.util.Collection;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kobkbs.graphsvc.fragment.SearchResultImpl;
import com.kobkbs.graphsvc.fragment.SearchResult.Result;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = "https://kob-kbs-react-5e24760e9974.herokuapp.com")
@CacheConfig(cacheNames = "searchResult")
@RestController
@RequestMapping("/v1/search")
@RequiredArgsConstructor
public class Search{

  private final SearchResultImpl search;

  @Cacheable(key = "#name")
  @GetMapping
  @ResponseBody
  public Collection<Result> searchEntity(@RequestParam String name) {
    return search.searchEntity(name);
  }
}
