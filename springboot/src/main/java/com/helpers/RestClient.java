package com.helpers;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * Created by vthakor on 5/2/18.
 */
@Component
@Getter
@Setter
public class RestClient {
    private RestTemplate basicRestTemplate;

    @Autowired
    public RestClient(RestTemplate restTemplate) {
        this.basicRestTemplate = restTemplate;
    }

    private <I> HttpEntity<I> buildHttpEntity() {
        return new HttpEntity<>(buildHttpHeaders());
    }

    private HttpHeaders buildHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }
    /**
     * Performs an HTTP GET request
     *
     * @param url
     * @param responseClass
     * @param <O>
     * @return
     */
    public <O> O get(String url, Class<O> responseClass) {
        return basicRestTemplate.exchange(url, HttpMethod.GET, buildHttpEntity(), responseClass).getBody();
    }

}
