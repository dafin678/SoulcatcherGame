package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> getUserByUsername(String username);
    void save(Optional<User> user);
}
