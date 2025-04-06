package com.david.shareNotes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.repositories.NoteRepository;
import com.david.shareNotes.repositories.UserRepository;
import com.david.shareNotes.types.notesParam;
import com.david.shareNotes.types.returnableNote;
import com.david.shareNotes.types.returnableUser;

@Service
public class NoteService {
    UserRepository userRepo;
    NoteRepository noteRepo;

    public NoteService(UserRepository repo, NoteRepository noteRepo) {
        this.userRepo = repo;
        this.noteRepo = noteRepo;
    }

    public returnableNote saveNote(notesParam note) {
        try {
            Notes cNote = new Notes(note.getTitle(), note.getTags(), note.getContenido(),
                    userRepo.findById(note.getUser_id()).get());
            // Make the retornable Note
            Notes repoNote = noteRepo.save(cNote);
            returnableNote retNote = new returnableNote(repoNote.getTitle(), repoNote.getContenido(),
                    repoNote.getTags(),
                    new returnableUser(repoNote.getUsuario().getName(), repoNote.getUsuario().getId()));
            return retNote;
        } catch (Error e) {
            throw new Error("Couldn't make the Note entity");
        }
    }

    public List<returnableNote> getAllNotes() {
        try {
            System.out.println("###################################################WFNJKLFKLNKNAFLFKLNWFKLNWF");
            List<Notes> notes = noteRepo.findAll();
            List<returnableNote> listRetNotes = new ArrayList<returnableNote>();
            // for every note we'll make a retNote object that we can show to the client.
            for (Notes note : notes) {
                returnableNote rNote = new returnableNote(note.getTitle(), note.getContenido(), note.getTags(),
                        new returnableUser(note.getUsuario().getName(), note.getUsuario().getId()));
                listRetNotes.add(rNote);
            }
            return listRetNotes;
        } catch (Error e) {
            throw new Error("INTERNAL SERVER ERROR, CANT FIND ANY NOTES");
        }
    }

    public returnableNote getNoteById(Long id) {
        try {
            Notes note = noteRepo.findById(id).get();
            return new returnableNote(note.getTitle(), note.getContenido(), note.getTags(),
                    new returnableUser(note.getUsuario().getName(), note.getUsuario().getId()));
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

    public returnableNote updateNote(Long id, notesParam note) {
        try {
            Notes repoNote = noteRepo.findById(id).get();
            repoNote.setTitle(note.getTitle());
            repoNote.setContenido(note.getContenido());
            repoNote.setTags(note.getTags());
            Notes savedNode = noteRepo.save(repoNote);
            return new returnableNote(savedNode.getTitle(), savedNode.getContenido(), savedNode.getTags(),
                    new returnableUser(savedNode.getUsuario().getName(), savedNode.getUsuario().getId()));
        } catch (Error e) {
            throw new Error("Couldn't update the note for the id: " + id);
        }
    }

    // find by Tag
    public List<returnableNote> findByTag(List<String> tags) {
        try {
            List<Notes> notes = noteRepo.findByTags(tags);
            List<returnableNote> listRetNotes = new ArrayList<returnableNote>();
            for (Notes note : notes) {
                listRetNotes.add(new returnableNote(note.getTitle(), note.getContenido(), note.getTags(),
                        new returnableUser(note.getUsuario().getName(), note.getUsuario().getId())));
            }
            return listRetNotes;
        } catch (Error e) {
            throw new Error("Couldn't find by tag");
        }
    }
}
