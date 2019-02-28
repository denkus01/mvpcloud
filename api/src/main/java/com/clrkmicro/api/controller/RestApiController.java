package com.clrkmicro.api.controller;


import com.clrkmicro.model.Document;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class RestApiController {
    @Autowired
    SearchInElasticController searchController;

    @RequestMapping(method = RequestMethod.GET, path = "/documents/{name}")
    public List<Document> listAllUsers(@PathVariable("name") String name) {
        log.info("Document was find: name ={}", name);
        return searchController.findDocuments(name);
    }
}
