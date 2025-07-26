package com.quizz_no_bolso.demo.service;

import org.springframework.stereotype.Service;

import com.quizz_no_bolso.demo.model.User;
import com.quizz_no_bolso.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import com.quizz_no_bolso.demo.shared.Utils;

@Service
public class UserService {

    private final UserRepository repository;
    
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User addUser(User userDetails) {
        User user = new User();
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        user.setEmail(userDetails.getEmail());
        user.setPassword(userDetails.getPassword());
        user.setId(Utils.generateRandomId());
        return repository.save(user);
    }

    public User getUserById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = (List<User>) repository.findAll();
        return users;
    }

    public User updateUser(String id, User user) {
        Optional<User> existingUser = repository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser.setFirstName(user.getFirstName());
            updatedUser.setLastName(user.getLastName());
            return repository.save(updatedUser);
        }
        return null;
    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }

    public boolean existsUser(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty.");
        }
        return repository.existsById(userId);
    }

}
