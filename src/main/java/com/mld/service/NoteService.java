package com.mld.service;

import com.mld.api.dto.NoteDTO;
import org.ektorp.DocumentNotFoundException;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Component
public class NoteService {

    private static final Logger log = getLogger(NoteService.class);

    @Autowired
    NotePersistenceHandler notePersistenceHandler;

    public NoteDTO createNote(String details){
        Note note = new Note(UUID.randomUUID().toString(), details);
        notePersistenceHandler.update(note);
        log.info("Note with id " + note.getId() + " created/updated.");
        return convertNoteToDTO(note);
    }

    public NoteDTO getNote(String id){

        Note note = null;

        try {
            note = notePersistenceHandler.get(id);
        }catch (DocumentNotFoundException dnfe){
            log.warn("Cannot find note with id : " + id);
            throw new NotFoundException("Cannot find note with id : " + id);
        }

        log.info("Found note with id: " + note.getId() + " with details: " + note.getDetails());
        return convertNoteToDTO(note);
    }

    private static NoteDTO convertNoteToDTO(Note note){
        return new NoteDTO(note.getId(), note.getDetails());
    }
}
