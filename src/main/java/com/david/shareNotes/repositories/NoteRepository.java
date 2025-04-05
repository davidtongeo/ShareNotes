package com.david.shareNotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.david.shareNotes.entities.Notes;

@Repository
public interface NoteRepository extends JpaRepository<Notes, Long> {
    @Query("SELECT n FROM Notes n WHERE EXISTS(SELECT t FROM n.tags t WHERE t IN ?1)")
    List<Notes> findByTags(List<String> tags);
}
