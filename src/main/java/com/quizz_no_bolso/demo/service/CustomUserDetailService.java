package com.quizz_no_bolso.demo.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quizz_no_bolso.demo.model.User;
import com.quizz_no_bolso.demo.repository.UserRepository;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String firstName) throws UsernameNotFoundException {
        User user = userRepository.findUserByFirstName(firstName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with first name: " + firstName);
        }
        return new org.springframework.security.core.userdetails.User(
            user.getFirstName(),
            user.getPassword(),
            new ArrayList<>()
        );

    }

}
