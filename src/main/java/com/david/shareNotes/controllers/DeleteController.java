package com.david.shareNotes.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.david.shareNotes.services.NoteService;

@RestController
public class DeleteController {
    NoteService noteService;

    public DeleteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @DeleteMapping("notes/{id}")
    public String deleteNote(@PathVariable Long id) {
        return noteService.deleteNote(id);
    }
}
