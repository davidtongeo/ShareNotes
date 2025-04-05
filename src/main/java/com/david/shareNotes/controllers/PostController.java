package com.david.shareNotes.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.shareNotes.entities.User;
import com.david.shareNotes.services.UserService;
import com.david.shareNotes.services.NoteService;
import com.david.shareNotes.types.notesParam;
import com.david.shareNotes.types.returnableNote;
import com.david.shareNotes.types.returnableUser;
import com.david.shareNotes.types.userParam;

@RestController
public class PostController {

    private final UserService userService;
    private final NoteService noteService;

    public PostController(UserService userService, NoteService noteService) {
        this.userService = userService;
        this.noteService = noteService;
    }

    @PostMapping("/loggin")
    // TODO: near future try to retrieve jwt
    public ResponseEntity<Object> performLoggin(@RequestBody userParam body) {
        try {
            returnableUser user = userService.authLoggin(body);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping("/note")
    public ResponseEntity<Object> saveNote(@RequestBody notesParam note) {
        try {
            returnableNote rNote = noteService.saveNote(note);
            return ResponseEntity.status(HttpStatus.CREATED).body(rNote);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> saveUser(@RequestBody userParam body) {
        User user = new User();
        // init
        user.setName(body.getName());
        user.setPassword(body.getPassword());
        try {
            user.setEmail(body.getEmail());
            returnableUser rUser = userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(rUser);
        } catch (Error e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }
    }
}
