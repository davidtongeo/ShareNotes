package com.david.shareNotes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.david.shareNotes.entities.Notes;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {
}
