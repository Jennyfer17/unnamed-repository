package com.quizz_no_bolso.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.quizz_no_bolso.demo.model.User;
import com.quizz_no_bolso.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import com.quizz_no_bolso.demo.model.request.UserDTO;
import com.quizz_no_bolso.demo.shared.Utils;

@Service
public class UserService {

    private final UserRepository repository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    public UserService(UserRepository repository, ModelMapper modelMapper, PasswordEncoder encoder) {
        this.repository = repository;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    public boolean existsByFirstName(String firstName) {
        return repository.existsByFirstName(firstName);
    }

    public User getUserByFirstName(String firstName) {
        return repository.findUserByFirstName(firstName);
    }
    public User addUser(UserDTO userDetails) {
        User user = modelMapper.map(userDetails, User.class);
        user.setId(Utils.generateRandomId());
        user.setPassword(encoder.encode(userDetails.getPassword()));
        return repository.save(user);
    }

    public User getUserById(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        List<User> users = (List<User>) repository.findAll();
        return users;
    }

    public User updateUser(String id, UserDTO user) {
        Optional<User> existingUser = repository.findById(id);
        if (existingUser.isPresent()) {
            User updatedUser = existingUser.get();
            updatedUser = modelMapper.map(updatedUser, User.class);
            // updatedUser.setFirstName(user.getFirstName());
            // updatedUser.setLastName(user.getLastName());
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
