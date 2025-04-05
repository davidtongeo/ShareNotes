package com.david.shareNotes.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.repositories.NoteRepository;
import com.david.shareNotes.repositories.UserRepository;
import com.david.shareNotes.types.notesParam;

@Service
public class NoteService {
    UserRepository userRepo;
    NoteRepository noteRepo;

    public NoteService(UserRepository repo, NoteRepository noteRepo) {
        this.userRepo = repo;
        this.noteRepo = noteRepo;
    }

    public Notes saveNote(notesParam note) {
        Notes cNote = new Notes();
        cNote.setContenido(note.getContenido());
        cNote.setTitle(note.getTitle());
        try {
            cNote.setUsuario(userRepo.findById(note.getUser_id()).get());
            return noteRepo.save(cNote);
        } catch (Error e) {
            throw new Error("Couldn't make the Note entity");
        }
    }

    public List<Notes> getAllNotes() {
        return noteRepo.findAll();
    }

    public Notes getNoteById(Long id) {
        try {
            return noteRepo.findById(id).get();
        } catch (Error e) {
            throw new Error("couldn't find the note by the id");
        }
    }

    public String deleteNote(Long id) {
        try {
            noteRepo.deleteById(id);
            return "Deleted!";
        } catch (Error e) {
            throw new Error("Couldn't find the note id, therefore it cant delete it");
        }
    }

    public Notes updateNote(Long id, notesParam note) {
        try {
            Notes rNote = noteRepo.findById(id).get();
            rNote.setTitle(note.getTitle());
            rNote.setContenido(note.getContenido());
            return noteRepo.save(rNote);
        } catch (Error e) {
            throw new Error("");
        }
    }
}
