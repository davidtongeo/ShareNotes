package com.david.shareNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.shareNotes.entities.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
}
