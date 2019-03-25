package com.controller;

import com.atom.GiphySearchItem;
import com.atom.GiphySearchResponse;
import com.services.GiphyService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by vthakor on 5/2/18.
 */
@RestController
@RequestMapping("/search")
public class GiphyController {

    private  GiphyService service;

    @Autowired
    public GiphyController(GiphyService giphyService) {
         this.service = giphyService;
    }

    /**
     * Searches the giphy database for the images matching the search term
     * @param searchTerm
     * @return
     */
    @GetMapping(value = "/{searchTerm}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GiphySearchResponse> search(@NotNull @NotBlank @PathVariable("searchTerm") String searchTerm)  {
        List<GiphySearchItem> searchResults = service.search(searchTerm);
        GiphySearchResponse response = new GiphySearchResponse();
        response.setData(searchResults);
        return new ResponseEntity<>(response, HttpStatus.valueOf(200));
    }

}
