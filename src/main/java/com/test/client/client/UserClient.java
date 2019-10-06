package com.test.client.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.test.client.common.TokenStorage;
import com.test.client.exception.MyClientException;
import com.test.client.exception.MyServerException;
import org.apache.http.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.*;

import org.apache.http.client.utils.*;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.methods.*;
import com.test.client.dto.*;
import org.apache.http.client.HttpClient;
import org.springframework.data.domain.Page;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;



public class UserClient {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final String scheme = "http" ;
    private static final String host = "127.0.0.1" ;
    private static final String port = "8080";
    private final static String pathAuthorization = "/server-test/private/user/authorization/";
    private final static String pathContentPage = "/server-test/private/user/content/page/";
    private final static String pathContent = "/server-test/private/user/content/";
    public void authorization(String login)throws URISyntaxException, IOException {


        URI uri = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPort(Integer.parseInt(port))
                .setPath(pathAuthorization)
                .build();

        AuthorizationDTO authorizationDTO = new AuthorizationDTO();
        authorizationDTO.setLogin(login);

        String json = objectMapper.writeValueAsString(authorizationDTO);

        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        HttpPost request = new HttpPost(uri);
        request.setEntity(entity);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);

        if(response.getStatusLine().getStatusCode() != 200)
            throw new MyServerException("status code:" + response.getStatusLine().getStatusCode() +
                                    "\nreason phrase:" + response.getStatusLine().getReasonPhrase(), response.getStatusLine().getStatusCode());

        TokenDTO tokenDTO = objectMapper.readValue(response.getEntity().getContent(), TokenDTO.class);
        TokenStorage.setToken(tokenDTO.getToken());
    }

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

    public void addUserContent(Long contentId)throws URISyntaxException, IOException {

        URI uri = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPort(Integer.parseInt(port))
                .setPath(pathContent)
                .build();

        IdDTO idDTO = new IdDTO();
        idDTO.setId(contentId);

        String json = objectMapper.writeValueAsString(idDTO);

        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        HttpPut request = new HttpPut(uri);
        request.addHeader("Authorization", "Bearer " + TokenStorage.getToken());
        request.setEntity(entity);

        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);

        if(response.getStatusLine().getStatusCode() != 201)
            throw new MyServerException("status code:" + response.getStatusLine().getStatusCode() +
                                    "\nreason phrase:" + response.getStatusLine().getReasonPhrase(), response.getStatusLine().getStatusCode());
    }

    public void deleteUserContent(Long contentId) throws URISyntaxException, IOException {

        URI uri = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPort(Integer.parseInt(port))
                .setPath(pathContent)
                .build();

        IdDTO idDTO = new IdDTO();
        idDTO.setId(contentId);

        String json = objectMapper.writeValueAsString(idDTO);

        StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
        HttpDeleteWithBody request = new HttpDeleteWithBody(uri);
        request.addHeader("Authorization", "Bearer " + TokenStorage.getToken());
        request.setEntity(entity);


        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(request);

        if(response.getStatusLine().getStatusCode() != 204)
            throw new MyServerException("status code:" + response.getStatusLine().getStatusCode() +
                                    "\nreason phrase:" + response.getStatusLine().getReasonPhrase(), response.getStatusLine().getStatusCode());

    }




}
