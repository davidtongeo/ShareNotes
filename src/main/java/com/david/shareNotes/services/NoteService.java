package com.david.shareNotes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.Like;
import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.entities.User;
import com.david.shareNotes.repositories.LikeRepository;
import com.david.shareNotes.repositories.NoteRepository;
import com.david.shareNotes.repositories.UserRepository;
import com.david.shareNotes.types.notesParam;
import com.david.shareNotes.types.returnableNote;
import com.david.shareNotes.types.returnableUser;

@Service
public class NoteService {
    UserRepository userRepo;
    NoteRepository noteRepo;
    LikeRepository likeRepo;
    LikeService likeService;

    public NoteService(UserRepository repo, NoteRepository noteRepo, LikeRepository likeRepo, LikeService likeService) {
        this.userRepo = repo;
        this.noteRepo = noteRepo;
        this.likeRepo = likeRepo;
        this.likeService = likeService;
    }

    public returnableNote saveNote(notesParam note) throws Exception {
        try {
            Notes cNote = new Notes(note.getTitle(), note.getTags(), note.getContenido(),

                    userRepo.findById(note.getUser_id()).get());
            // Make the retornable Note
            Notes repoNote = noteRepo.save(cNote);
            returnableNote retNote = new returnableNote(repoNote.getTitle(), repoNote.getContenido(),
                    repoNote.getTags(),
                    new returnableUser(repoNote.getUsuario().getName(), repoNote.getUsuario().getId()),
                    repoNote.getId(), repoNote.getLikes());
            return retNote;
        } catch (Exception e) {
            throw new Exception("Couldn't make the Note entity");
        }
    }

    public returnableNote likeNote(Long user, Long note) throws Exception {
        Like returnedLike;
        Notes returnedNote;
        try {
            returnedLike = likeService.returnLike(user, note);
        } catch (Exception e) {
            throw new Exception(e);
        }
        if (returnedLike == null) {
            // find the note
            try {
                returnedNote = noteRepo.findById(note).get();
            } catch (Exception e) {
                throw new Exception("Couldn't find any note with that id: " + note);
            }
            returnedNote.setLikes(returnedNote.getLikes() + 1);
            // save the note
            try {
                returnedNote = noteRepo.save(returnedNote);
                User toSaveToLikeUser;
                try {
                    toSaveToLikeUser = userRepo.findById(user).get();
                } catch (Exception e) {
                    throw new Exception("Can't find a user with that id for the like, id: " + user);
                }
                Like toSaveLike = new Like(toSaveToLikeUser, returnedNote);
                try {
                    likeRepo.save(toSaveLike);
                } catch (Exception e) {
                    throw new Exception("Cant save the like. refer to the NoteService");
                }
            } catch (Exception e) {
                throw new Exception("Cant save the note with id: " + note);
            }
        } else {
            throw new Exception("Can't like the note with the id: " + note);
        }
        return new returnableNote(returnedNote.getTitle(), returnedNote.getContenido(), returnedNote.getTags(),
                new returnableUser(returnedNote.getUsuario().getName(), returnedNote.getUsuario().getId()),
                returnedNote.getId(), returnedNote.getLikes());
    }

    public List<returnableNote> getAllNotes() throws Exception {
        try {
            List<Notes> notes = noteRepo.findAll();
            List<returnableNote> listRetNotes = new ArrayList<returnableNote>();
            // for every note we'll make a retNote object that we can show to the client.
            for (Notes note : notes) {
                returnableNote rNote = new returnableNote(note.getTitle(), note.getContenido(), note.getTags(),
                        new returnableUser(note.getUsuario().getName(), note.getUsuario().getId()), note.getId(),
                        note.getLikes());
                listRetNotes.add(rNote);
            }
            return listRetNotes;
        } catch (Exception e) {
            throw new Exception("INTERNAL SERVER ERROR, CANT FIND ANY NOTES");
        }
    }

    public returnableNote getNoteById(Long id) throws Exception {
        try {
            Notes note = noteRepo.findById(id).get();
            return new returnableNote(note.getTitle(), note.getContenido(), note.getTags(),
                    new returnableUser(note.getUsuario().getName(), note.getUsuario().getId()), note.getId(),
                    note.getLikes());
        } catch (Exception e) {
            throw new Exception("couldn't find the note by the id");
        }
    }

    public void deleteNote(Long id) throws Exception {
        try {
            noteRepo.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Couldn't find the note id, therefore it cant delete it");
        }
    }

    public returnableNote updateNote(Long id, notesParam note) throws Exception {
        try {
            Notes repoNote = noteRepo.findById(id).get();
            repoNote.setTitle(note.getTitle());
            repoNote.setContenido(note.getContenido());
            repoNote.setTags(note.getTags());
            Notes savedNode = noteRepo.save(repoNote);
            return new returnableNote(savedNode.getTitle(), savedNode.getContenido(), savedNode.getTags(),
                    new returnableUser(savedNode.getUsuario().getName(), savedNode.getUsuario().getId()),
                    savedNode.getId(), savedNode.getLikes());
        } catch (Exception e) {
            throw new Exception("Couldn't update the note for the id: " + id);
        }
    }

    // find by Tag
    public List<returnableNote> findByTag(List<String> tags) throws Exception {
        try {
            List<Notes> notes = noteRepo.findByTags(tags);
            List<returnableNote> listRetNotes = new ArrayList<returnableNote>();
            for (Notes note : notes) {
                listRetNotes.add(new returnableNote(note.getTitle(), note.getContenido(), note.getTags(),
                        new returnableUser(note.getUsuario().getName(), note.getUsuario().getId()), note.getId(),
                        note.getLikes()));
            }
            return listRetNotes;
        } catch (Exception e) {
            throw new Exception("Couldn't find by tag");
        }
    }
}
