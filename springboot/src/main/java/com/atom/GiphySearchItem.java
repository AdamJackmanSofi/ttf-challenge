package com.atom;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.io.Serializable;

/**
 * Created by vthakor on 5/2/18.
 */
@JsonPropertyOrder({"gif_id","url"})
public class GiphySearchItem implements Serializable{

    private static final long serialVersionUID = -8853385410835002736L;

    private String id;

    private String url;

    @JsonProperty("gif_id")
    public String getId() {
        return id;
    }
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
