package com.david.shareNotes.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.david.shareNotes.entities.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
    @Query("SELECT l FROM Like l WHERE l.user.id = :userId AND l.note.id = :noteId")

    // LSP source doesn't show after deleting in `normal` mode (at least in this
    // case), but it does after deleting the word `insert` mode.
    public Optional<Like> findByUserIdAndNoteId(Long userId, Long noteId);
}
