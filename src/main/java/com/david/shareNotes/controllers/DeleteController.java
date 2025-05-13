package com.david.shareNotes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.shareNotes.services.LikeService;
import com.david.shareNotes.services.NoteService;
import com.david.shareNotes.types.likeType;

@RestController
public class DeleteController {
    private final NoteService noteService;
    private final LikeService likeService;

    public DeleteController(NoteService noteService, LikeService likeService) {
        this.noteService = noteService;
        this.likeService = likeService;
    }

    @DeleteMapping("notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        try {
            noteService.deleteNote(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/like")
    public ResponseEntity<Void> deleteLike(@RequestBody likeType like) {
        try {
            likeService.unLike(like.getIdUser(), like.getIdNote());
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
