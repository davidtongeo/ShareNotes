package com.david.shareNotes.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.david.shareNotes.entities.Like;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    public List<Like> findByNoteId(Long noteId);

    @Query("SELECT l FROM Like l WHERE l.user.id = :userId AND l.note.id = :noteId")
    public Optional<Like> findByUserIdAndNoteId(Long userId, Long noteId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Like l WHERE l.user.id = :userId AND l.note.id = :noteId")
    void unlike(@Param("userId") Long userId, @Param("noteId") Long noteId);

    @Query("SELECT COUNT(l) FROM Like l WHERE l.user.id = :userId AND l.note.id = :noteId")
    Long thereIsLike(@Param("userId") Long userId, @Param("noteId") Long noteId);
}
