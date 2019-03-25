package com.configuration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by vthakor on 5/2/18.
 */
@Configuration
public class RestTemplateConfig {
    private static RestTemplate basicRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        httpMessageConverters.add(jsonConverter);
        httpMessageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(httpMessageConverters);
        return restTemplate;
    }
    @Bean
    @Qualifier("basicRestTemplate")
    public RestTemplate restTemplate() {
        return basicRestTemplate();
    }

}
