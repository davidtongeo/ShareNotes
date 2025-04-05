package com.david.shareNotes.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.services.NoteService;

@RestController
public class GetController {
    private final NoteService noteService;

    public GetController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/notes")
    // TODO: tags is not doing anything
    public List<Notes> getAllNotes(@RequestParam(required = false) List<String> tags) {
        return noteService.getAllNotes();
    }

    @GetMapping("/notes/{id}")
    public Notes getSingleNote(@PathVariable Long id) {
        return noteService.getNoteById(id);
    }
}
