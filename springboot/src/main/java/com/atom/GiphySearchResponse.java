package com.atom;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by vthakor on 5/2/18.
 */
@Getter
@Setter
public class GiphySearchResponse implements Serializable{
    private static final long serialVersionUID = -5963947112853379988L;

    private List<GiphySearchItem> data;
}
