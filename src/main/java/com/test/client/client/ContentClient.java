package com.test.client.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.client.common.TokenStorage;
import com.test.client.dto.*;

import com.test.client.exception.MyServerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

import org.apache.http.client.utils.URIBuilder;

import org.apache.http.impl.client.HttpClientBuilder;

import org.springframework.data.domain.Page;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class ContentClient {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String scheme = "http" ;
    private static final String host = "127.0.0.1" ;
    private static final String port = "8080";


    private final static String pathContentPage = "/server-test/private/content/page/";



    public Page<ContentDTO> getContentPage(int pageNum, int pageSize)throws URISyntaxException, IOException {

        URI uri = new URIBuilder()
                        .setScheme(scheme)
                        .setHost(host)
                        .setPort(Integer.parseInt(port))
                        .setPath(pathContentPage)
                        .addParameter("pageNum", String.valueOf(pageNum))
                        .addParameter("pageSize", String.valueOf(pageSize))
                        .build();


        HttpGet request = new HttpGet(uri);
        request.addHeader("Authorization", "Bearer " + TokenStorage.getToken());

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);

        if(response.getStatusLine().getStatusCode() != 200)
            throw new MyServerException("status code:" + response.getStatusLine().getStatusCode() +
                                        "\nreason phrase:" + response.getStatusLine().getReasonPhrase(), response.getStatusLine().getStatusCode());

        RestPageImpl<ContentDTO> result = objectMapper.readValue(response.getEntity().getContent(), new TypeReference<RestPageImpl<ContentDTO>>() {
        });

        return result;
    }

}
