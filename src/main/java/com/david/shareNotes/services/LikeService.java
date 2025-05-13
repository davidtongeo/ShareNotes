package com.david.shareNotes.services;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.Like;
import com.david.shareNotes.entities.User;
import com.david.shareNotes.entities.Notes;
import com.david.shareNotes.repositories.LikeRepository;
import com.david.shareNotes.repositories.NoteRepository;
import com.david.shareNotes.repositories.UserRepository;

@Service
public class LikeService {
    public final LikeRepository likeRepo;
    public final UserRepository userRepository;
    public final NoteRepository noteRepository;

    public LikeService(LikeRepository likeRepo, UserRepository userRepository, NoteRepository noteRepository) {
        this.likeRepo = likeRepo;
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
    }

    public Like returnLike(Long user, Long note) throws Exception {
        Like obtainedLike;
        try {
            if (likeRepo.findByUserIdAndNoteId(user, note).isPresent()) {
                obtainedLike = likeRepo.findByUserIdAndNoteId(user, note).get();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Exception("Couldn't find any like. " + e);
        }
        return obtainedLike;
    }

    public void unLike(Long user, Long note) throws Exception {
        try {
            likeRepo.unlike(user, note);
        } catch (Exception e) {
            throw new Exception("Can't delete the like, refer to the LikeRepository");
        }
    }

    public void like(Long user, Long note) throws Exception {
        try {
            // find user
            User returnedUser = userRepository.findById(user).orElseThrow(() -> new Exception("Can't Find the user"));
            // find note
            Notes returnedNote = noteRepository.findById(note).orElseThrow(() -> new Exception("Can't find the note"));
            System.out.println(likeRepo.thereIsLike(user, note));
            if (likeRepo.thereIsLike(user, note) == 0) {
                System.out.println("performed Like");
                Like like = new Like(returnedUser, returnedNote);
                likeRepo.save(like);
            } else {
                System.out.println("performed Unlike");
                likeRepo.unlike(user, note);
            }
        } catch (Exception e) {
            throw new Exception("Default case, couldn't perform like, refer to NoteService.java");
        }
    }

    public Long getAmount(Long noteId) throws Exception {
        return (long) likeRepo.findByNoteId(noteId).size();
    }
}
