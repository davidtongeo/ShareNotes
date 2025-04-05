package com.david.shareNotes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.services.NoteService;
import com.david.shareNotes.types.notesParam;

@RestController
public class PutController {
    NoteService noteService;

    public PutController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Object> updateNote(@PathVariable Long id, @RequestBody notesParam note) {
        try {
            Notes rNote = noteService.updateNote(id, note);
            return ResponseEntity.status(HttpStatus.OK).body(rNote);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
