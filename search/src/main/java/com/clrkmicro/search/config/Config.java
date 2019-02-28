package com.clrkmicro.search.config;

import com.google.gson.Gson;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@PropertySource(value = {"classpath:/connection.properties"})
@Configuration
public class Config {
    private static final String SCHEME = "http";

    @Value("${com.clrkmicro.search.port}")
    private Integer portAdress;

    //@Value("${com.clrkmicro.search.host}")
    private String host = "127.0.0.1";

//    @Bean
//    public TransportClient transportClient() throws UnknownHostException {
//        return new PreBuiltTransportClient(Settings.EMPTY)
//                .addTransportAddress(new TransportAddress(InetAddress.getByName(host),
//                        portAdress));
//    }

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")));
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
}
