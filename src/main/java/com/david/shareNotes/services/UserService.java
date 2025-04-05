package com.david.shareNotes.services;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.User;
import com.david.shareNotes.repositories.UserRepository;
import com.david.shareNotes.types.userParam;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User saveUser(User user) {
        if (user == null)
            throw new Error("The user was null");
        if (userRepo.findByEmailAdress(user.getEmail()).size() > 0)
            throw new Error("The email is already signed.");
        return userRepo.save(user);
    }

    public User authLoggin(userParam user) {
        if (user == null)
            throw new Error("The user was null");
        User findedUser = userRepo.findUser(user.getEmail(), user.getPassword());
        if (findedUser == null)
            throw new Error("No user was found.");
        return findedUser;
    }
}
