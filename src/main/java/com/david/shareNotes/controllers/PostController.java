package com.david.shareNotes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.entities.User;
import com.david.shareNotes.services.UserService;
import com.david.shareNotes.services.NoteService;
import com.david.shareNotes.types.notesParam;
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
    // TODO: we need to have some form of body which will have the neccesary info to
    // loggin
    public User performLoggin(@RequestBody userParam body) {
        try {
            return userService.authLoggin(body);
        } catch (Error e) {
            // TODO: make a proper error message;
            throw new Error("");
        }
    }

    @PostMapping("/note")
    public Notes saveNote(@RequestBody notesParam note) {
        try {
            return noteService.saveNote(note);
        } catch (Error e) {
            // TODO: make a proper error message;
            throw new Error("");
        }
    }

    @PostMapping("/register")
    public User saveUser(@RequestBody userParam body) {
        User user = new User();
        // init
        user.setName(body.getName());
        user.setPassword(body.getPassword());
        try {
            user.setEmail(body.getEmail());
            return userService.saveUser(user);
        } catch (Error e) {
            // TODO: make a proper error message;
            throw new Error("");
        }
    }
}
