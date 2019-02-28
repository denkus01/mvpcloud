package com.clrkmicro.search.controller;

import com.clrkmicro.model.Document;
import com.clrkmicro.search.service.SearchDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SearchInElasticControllerImpl {

    private final SearchDocumentService searchDocumentService;

    @Autowired
    public SearchInElasticControllerImpl(SearchDocumentService searchDocumentService) {
        this.searchDocumentService = searchDocumentService;
    }

    @GetMapping("/documents/{name}")
    List<Document> findAllDocument(@PathVariable("name") String name) throws IOException {
        return searchDocumentService.findDocuments(name);
    }
}
