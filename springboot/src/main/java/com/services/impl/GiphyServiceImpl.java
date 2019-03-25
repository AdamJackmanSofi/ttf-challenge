package com.services.impl;

import com.atom.GiphySearchItem;
import com.atom.GiphySearchResponse;
import com.helpers.RestClient;
import com.services.GiphyService;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vthakor on 5/2/18.
 */
@Service
@NoArgsConstructor
public class GiphyServiceImpl implements GiphyService {
    private static final int LIMIT = 6;

    private String giphySearchUrl;

    private String giphyApiKey;

    private RestClient restClient;

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    @Autowired
    public GiphyServiceImpl(@Value("${giphy.url}") String giphySearchUrl
            ,@Value("${giphy.key}") String giphyApiKey
            ,RestClient restClient) {
        this.giphySearchUrl = giphySearchUrl;
        this.giphyApiKey = giphyApiKey;
        this.restClient = restClient;
    }

    /**
     * Searches the giphy database for the images matching the search term
     *
     * @param searchTerm
     * @return
     */
    @Override
    public List<GiphySearchItem> search(String searchTerm) {
        List<GiphySearchItem> response = new ArrayList<>();
        try {
            // only fetch at most 6 results as only first 5 items will be shown
            String url = UriComponentsBuilder.fromHttpUrl(String.format(giphySearchUrl, giphyApiKey, searchTerm, LIMIT))
                    .build()
                    .toUriString();
            GiphySearchResponse giphySearchResponse = restClient.get(url, GiphySearchResponse.class);
            // if more than 5 results then only return the first 5
            if (giphySearchResponse != null && giphySearchResponse.getData() != null) {
                if (giphySearchResponse.getData().size() > 5)
                    return giphySearchResponse.getData().subList(0, 5);
            }
        }catch (Exception e){
            logger.error(e.getMessage(),e);
        }

        return response;
    }
}
