package com.services;

import com.atom.GiphySearchItem;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vthakor on 5/2/18.
 */
public interface GiphyService {
    /**
     * Searches the giphy database for the images matching the search term
     * @param searchTerm
     * @return
     */
    List<GiphySearchItem> search (String searchTerm);
}
