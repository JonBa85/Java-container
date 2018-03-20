package com.mld.api;

import com.mld.api.dto.NoteDTO;
import com.mld.service.NotFoundException;
import com.mld.service.NoteService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class NotesController {

    @Autowired
    private NoteService noteService;

    private static final Logger log = getLogger(NotesController.class);

    @ApiOperation(value = "Attempts to find a note by id", response = NoteDTO.class)
    @RequestMapping(value = "/", method = GET, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDTO> findNote(@RequestParam("id") String id) {
        return ResponseEntity.ok(noteService.getNote(id));
    }

    @ApiOperation(value = "Creates a new note", response = NoteDTO.class )
    @RequestMapping(value = "/", method = POST, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDTO> saveNote(@RequestBody() String details) {
        return ResponseEntity.ok(noteService.createNote(details));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public void handleException(Exception e) {
        log.warn("An unexpected error occurred", e);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public void handleNotFoundException(Exception e) {
        log.warn("Could not find requested NoteDTO", e);
    }
}
