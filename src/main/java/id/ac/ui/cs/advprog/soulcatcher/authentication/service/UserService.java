package id.ac.ui.cs.advprog.soulcatcher.authentication.service;

import id.ac.ui.cs.advprog.soulcatcher.authentication.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUsername(String username);
    void save(Optional<User> user);
}
