package com.jamadev.firstapi.controllers;

import com.jamadev.firstapi.config.JwtTokenProvider;
import com.jamadev.firstapi.dto.auth.LoginRequest;
import com.jamadev.firstapi.dto.auth.SignupRequest;
import com.jamadev.firstapi.dto.global.Result;
import com.jamadev.firstapi.models.User;
import com.jamadev.firstapi.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Result<String>> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        try {
        String token = tokenProvider.generateToken(loginRequest.getEmail());
        return ResponseEntity.ok(Result.success(token));

        } catch (Exception e) {
            return ResponseEntity.ok(Result.success(e.getMessage()));

        }

    }

    @PostMapping("/signup")
    @PreAuthorize("permitAll()")
    public ResponseEntity<Result<User>> registerUser(@RequestBody SignupRequest signupRequest) {
        if (userRepository.findByEmail(signupRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Result.failure("Email address already in use", HttpStatus.CONFLICT.value()));
        }

        User user = new User();
        user.setName(signupRequest.getName());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));

        User savedUser = userRepository.save(user);
        return ResponseEntity.ok(Result.success(savedUser));
    }

}
