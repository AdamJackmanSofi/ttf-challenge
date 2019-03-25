package com.services.impl;


import com.atom.GiphySearchItem;
import com.atom.GiphySearchResponse;
import com.helpers.RestClient;
import lombok.Getter;
import lombok.Setter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;

/**
 * Created by vthakor on 5/3/18.
 */
@RunWith(SpringRunner.class)
@Getter
@Setter
public class GiphyServiceImplTest {

    private static final String searchTerm = "test";
    private static final String giphySearchUrl = "http://api.giphy.com/v1/gifs/search?api_key=%s&q=%s&limit=%d";
    private static final String giphyApiKey = "test";

    private GiphyServiceImpl giphyService;
    @Mock
    private RestClient restClient;

    private static final String url= "http://api.giphy.com/v1/gifs/search?api_key="+giphyApiKey+"&q="+searchTerm+"&limit=6";

    @Before
    public void setUp() throws Exception {
        giphyService = new GiphyServiceImpl(giphySearchUrl,giphyApiKey, restClient);

    }
    @Test
    public void whenMorethan5Results() {
        List<GiphySearchItem> results=  IntStream.rangeClosed(1, 6).mapToObj(i -> {
            GiphySearchItem giphySearchItem = new GiphySearchItem();
            giphySearchItem.setId("testid_"+i);
            giphySearchItem.setUrl("imgUrl");
            return giphySearchItem;
        }).collect(Collectors.toList());
        GiphySearchResponse giphySearchResponse = new GiphySearchResponse();
        giphySearchResponse.setData(results);
        Mockito.when(restClient.get(url,GiphySearchResponse.class)).thenReturn(giphySearchResponse);
        List<GiphySearchItem> found = giphyService.search(searchTerm);

        assertEquals(5,found.size());
    }
    @Test
    public void whenNoResultsAreReturned() {

        Mockito.when(restClient.get(url,GiphySearchResponse.class)).thenReturn(new GiphySearchResponse());
        List<GiphySearchItem> found = giphyService.search(searchTerm);

        assertEquals(0,found.size());
    }

    @Test
    public void whenLessThan5ResultsAreReturned() {
        List<GiphySearchItem> results=  IntStream.rangeClosed(1, 3).mapToObj(i -> {
            GiphySearchItem giphySearchItem = new GiphySearchItem();
            giphySearchItem.setId("testid_"+i);
            giphySearchItem.setUrl("imgUrl");
            return giphySearchItem;
        }).collect(Collectors.toList());
        GiphySearchResponse giphySearchResponse = new GiphySearchResponse();
        giphySearchResponse.setData(results);
        Mockito.when(restClient.get(url,GiphySearchResponse.class)).thenReturn(giphySearchResponse);

        List<GiphySearchItem> found = giphyService.search(searchTerm);

        assertEquals(0,found.size());
    }



}