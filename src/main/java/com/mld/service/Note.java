package com.mld.service;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.ektorp.support.CouchDbDocument;

public class Note extends CouchDbDocument {

    public String noteId;
    public String details;

    @JsonCreator
    public Note(@JsonProperty("noteId") String noteId, @JsonProperty("details") String details) {
        this.noteId = noteId;
        this.details = details;
        this.setId(noteId);
    }

    public String getNoteId() {
        return noteId;
    }

    public String getDetails() {
        return details;
    }
}

