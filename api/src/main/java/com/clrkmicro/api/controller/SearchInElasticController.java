package com.clrkmicro.api.controller;

import com.clrkmicro.model.Document;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "search-service")
public interface SearchInElasticController {
    @RequestMapping(method = RequestMethod.GET, value = "/documents/{name}")
    List<Document> findDocuments(@PathVariable("name") String name);
}
