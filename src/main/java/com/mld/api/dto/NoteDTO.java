package com.mld.api.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;

@ApiModel(value = "NoteDTO")
public class NoteDTO {

    public String id;
    public String details;

    @JsonCreator
    public NoteDTO(@JsonProperty("id")String id, @JsonProperty("details")String details) {
        this.id = id;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public String getDetails() {
        return details;
    }
}

