package com.david.shareNotes.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.services.NoteService;
import com.david.shareNotes.types.notesParam;

@RestController
public class GetController {
    private final NoteService noteService;

    public GetController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    public ResponseEntity<Object> getAllNotes(@RequestBody(required = false) notesParam note) {
        if (note == null) {
            // No parameters were found.
            try {
                List<Notes> notes = noteService.getAllNotes();
                return ResponseEntity.status(HttpStatus.FOUND).body(notes);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
            }
        } else {
            try {
                List<Notes> notes = noteService.findByTag(note.getTags());
                return ResponseEntity.status(HttpStatus.FOUND).body(notes);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
            }
        }
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Object> getSingleNote(@PathVariable Long id) {
        try {
            Notes note = noteService.getNoteById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(note);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
