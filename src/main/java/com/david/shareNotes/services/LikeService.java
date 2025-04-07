package com.david.shareNotes.services;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.Like;
import com.david.shareNotes.repositories.LikeRepository;

@Service
public class LikeService {
    LikeRepository likeRepo;

    public LikeService(LikeRepository likeRepo) {
        this.likeRepo = likeRepo;
    }

    public Like returnLike(Long user, Long note) {
        Like obtainedLike;
        try {
            if (likeRepo.findByUserIdAndNoteId(user, note).isPresent()) {
                obtainedLike = likeRepo.findByUserIdAndNoteId(user, note).get();
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new Error("Couldn't find any like. " + e);
        }
        return obtainedLike;
    }
}
