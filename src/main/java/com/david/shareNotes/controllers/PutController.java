package com.david.shareNotes.controllers;

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
    public Notes updateNote(@PathVariable Long id, @RequestBody notesParam note) {
        return noteService.updateNote(id, note);
    }
}
