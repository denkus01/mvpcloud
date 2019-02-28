package com.clrkmicro.search.service;


import com.clrkmicro.model.Document;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class SearchDocumentService {
    private static final String INDEX = "documents";

    private final RestHighLevelClient restHighLevelClient;
    private final Gson gson;

    @Autowired
    public SearchDocumentService(RestHighLevelClient restHighLevelClient, Gson gson) {
        this.restHighLevelClient = restHighLevelClient;
        this.gson = gson;
    }

    public List<Document> findDocuments(final String name) throws IOException {
        log.debug("File document name {}", name);
        List<Document> result = new ArrayList<>();

        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder()
                .query(QueryBuilders.matchQuery("name", name))
                .timeout(new TimeValue(60, TimeUnit.SECONDS));

        SearchRequest searchRequest = new SearchRequest()
                .indices(INDEX)
                .source(searchSourceBuilder);

        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        log.debug("SearchResponse {}", searchResponse.getHits().getAt(0).getSourceAsString());

        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits) {
            Document doc = gson.fromJson(hit.getSourceAsString(), Document.class);
            result.add(doc);
        }
        log.debug("SearchHit", hits.totalHits);
        return result;
    }
}
