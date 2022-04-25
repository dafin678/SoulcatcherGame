package id.ac.ui.cs.advprog.soulcatcher.service;

import id.ac.ui.cs.advprog.soulcatcher.model.User;
import id.ac.ui.cs.advprog.soulcatcher.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(Optional<User> user) {
        User userValue = null;

        if(user.isPresent()) {
            userValue = user.get();
            userRepository.save(userValue);
        }
    }
}
