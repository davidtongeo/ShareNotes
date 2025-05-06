package com.david.shareNotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.david.shareNotes.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u where u.email = ?1")
    List<User> findByEmailAdress(String email);

    @Query("SELECT u FROM User u WHERE LOWER(u.name) = LOWER(?1) AND u.password = ?2")
    User findUser(String name, String password);
}
