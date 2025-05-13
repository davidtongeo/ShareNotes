package com.david.shareNotes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.Notes;
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
    UserService userService;
    CommentService commentService;

    public NoteService(UserRepository repo, NoteRepository noteRepo, LikeRepository likeRepo, LikeService likeService,
            UserService userService, CommentService commentService) {
        this.userRepo = repo;
        this.noteRepo = noteRepo;
        this.likeRepo = likeRepo;
        this.likeService = likeService;
        this.userService = userService;
        this.commentService = commentService;
    }

    public returnableNote saveNote(notesParam note) throws Exception {
        try {
            Notes cNote = new Notes(note.getTitle(), note.getTags(), note.getContenido(),

                    userRepo.findById(note.getUser_id()).get());
            // Make the retornable Note
            Notes repoNote = noteRepo.save(cNote);
            returnableNote retNote = new returnableNote(repoNote.getTitle(), repoNote.getContenido(),
                    repoNote.getTags(),
                    new returnableUser(repoNote.getUsuario().getName(), repoNote.getUsuario().getId(),
                            userService.checkIfAdmin(repoNote.getUsuario().getId())),
                    repoNote.getId(), repoNote.getLikes(), commentService.getByCommentId(repoNote.getId()));
            return retNote;
        } catch (Exception e) {
            throw new Exception("Couldn't make the Note entity");
        }
    }

    public void likeNote(Long userId, Long noteId) throws Exception {
        try {
            likeService.like(userId, noteId);
        } catch (Exception e) {
            throw new Exception("Cant perform like, refer to Likenote: NoteService");
        }
    }

    public List<returnableNote> getAllNotes() throws Exception {
        try {
            List<Notes> notes = noteRepo.findAll();
            List<returnableNote> listRetNotes = new ArrayList<returnableNote>();
            // for every note we'll make a retNote object that we can show to the client.
            for (Notes note : notes) {
                returnableNote rNote = new returnableNote(note.getTitle(), note.getContenido(), note.getTags(),
                        new returnableUser(note.getUsuario().getName(), note.getUsuario().getId(),
                                userService.checkIfAdmin(note.getUsuario().getId())),
                        note.getId(),
                        likeService.getAmount(note.getId()), commentService.getByCommentId(note.getId()));
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
                    new returnableUser(note.getUsuario().getName(), note.getUsuario().getId(),
                            userService.checkIfAdmin(note.getUsuario().getId())),
                    note.getId(),
                    note.getLikes(), commentService.getByCommentId(note.getId()));
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
                    new returnableUser(savedNode.getUsuario().getName(), savedNode.getUsuario().getId(),
                            userService.checkIfAdmin(savedNode.getUsuario().getId())),
                    savedNode.getId(), savedNode.getLikes(), commentService.getByCommentId(savedNode.getId()));
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
                        new returnableUser(note.getUsuario().getName(), note.getUsuario().getId(),
                                userService.checkIfAdmin(note.getUsuario().getId())),
                        note.getId(),
                        note.getLikes(), commentService.getByCommentId(note.getId())));
            }
            return listRetNotes;
        } catch (Exception e) {
            throw new Exception("Couldn't find by tag");
        }
    }
}
