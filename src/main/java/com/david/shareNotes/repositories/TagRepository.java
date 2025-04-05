package com.david.shareNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.david.shareNotes.entities.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
}
