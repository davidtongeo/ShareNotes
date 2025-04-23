package com.david.shareNotes.services;

import org.springframework.stereotype.Service;

import com.david.shareNotes.entities.User;
import com.david.shareNotes.repositories.UserRepository;
import com.david.shareNotes.types.returnableUser;
import com.david.shareNotes.types.userParam;

@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public returnableUser saveUser(User user) throws Exception {
        if (user == null)
            throw new Exception("The user was null");
        if (userRepo.findByEmailAdress(user.getEmail()).size() > 0)
            throw new Error("The email is already signed.");
        try {
            User repoUser = userRepo.save(user);
            return new returnableUser(repoUser.getName(), repoUser.getId());
        } catch (Exception e) {
            throw new Exception("INTERNAL SERVER ERROR, couldn't save to the database.");
        }
    }

    public returnableUser authLoggin(userParam user) throws Exception {
        if (user == null)
            throw new Error("The user was null.");
        try {
            User foundedUser = userRepo.findUser(user.getEmail(), user.getPassword());
            if (foundedUser == null)
                throw new Exception("No user was found.");
            return new returnableUser(foundedUser.getName(), foundedUser.getId());
        } catch (Exception e) {
            throw new Exception("couldn't log-in.");
        }
    }
}
