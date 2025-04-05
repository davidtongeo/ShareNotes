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
        cNote.setTags(note.getTags());
        try {
            cNote.setUsuario(userRepo.findById(note.getUser_id()).get());
            // hide User
            Notes rNote = noteRepo.save(cNote);
            rNote.setUsuario(null);
            return rNote;
        } catch (Error e) {
            throw new Error("Couldn't make the Note entity");
        }
    }

    public List<Notes> getAllNotes() {
        List<Notes> notes = noteRepo.findAll();
        for (Notes note : notes) {
            note.setUsuario(null);
        }
        return notes;
    }

    public Notes getNoteById(Long id) {
        try {
            Notes note = noteRepo.findById(id).get();
            note.setUsuario(null);
            return note;
        } catch (Error e) {
            throw new Error("couldn't find the note by the id");
        }
    }

    public void deleteNote(Long id) {
        try {
            noteRepo.deleteById(id);
        } catch (Error e) {
            throw new Error("Couldn't find the note id, therefore it cant delete it");
        }
    }

    public Notes updateNote(Long id, notesParam note) {
        try {
            Notes rNote = noteRepo.findById(id).get();
            rNote.setTitle(note.getTitle());
            rNote.setContenido(note.getContenido());
            rNote.setTags(note.getTags());
            return noteRepo.save(rNote);
        } catch (Error e) {
            throw new Error("");
        }
    }

    // find by Tag
    public List<Notes> findByTag(List<String> tags) {
        try {
            return noteRepo.findByTags(tags);
        } catch (Error e) {
            throw new Error("Couldn't find by tag");
        }
    }
}
