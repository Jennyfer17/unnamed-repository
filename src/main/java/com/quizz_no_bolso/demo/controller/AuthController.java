package com.quizz_no_bolso.demo.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizz_no_bolso.demo.model.User;
import com.quizz_no_bolso.demo.model.request.UserDTO;
import com.quizz_no_bolso.demo.security.JwtUtil;
import com.quizz_no_bolso.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signin")
    public String authenticateUser (@RequestBody @Valid UserDTO user) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                user.getFirstName(),
                user.getPassword()
            )
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtil.generateToken(userDetails.getUsername());
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@RequestBody @Valid UserDTO user) {
        if (userService.existsByFirstName(user.getFirstName())) {
            return ResponseEntity.badRequest().build();
        }
        User newUser = userService.addUser(user);
        return ResponseEntity.ok(newUser);
    }
}
