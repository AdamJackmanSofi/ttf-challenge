package com.controller;


import com.atom.GiphySearchItem;
import com.services.GiphyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by vthakor on 5/3/18.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(GiphyController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-snippets")
public class GiphyControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GiphyService service;

    @Test
    public void whenSearchResultsReturnResults() throws Exception {
        GiphySearchItem searchItem = new GiphySearchItem();
        searchItem.setId("img123");
        searchItem.setUrl("url");

        List<GiphySearchItem> searchItemList = Arrays.asList(searchItem);

        when(service.search(anyString())).thenReturn(searchItemList);

        mvc.perform(get("/search/test").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].url").value("url"))
                .andExpect(jsonPath("$.data[0].gif_id").value("img123"))
                .andDo(document("search"))
        ;

    }
}