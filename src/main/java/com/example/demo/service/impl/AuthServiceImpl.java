package com.example.demo.service.impl;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AuthResponseDTO signin(AuthRequestDTO authRequestDTO) {

        User user = userRepository.findByEmail(authRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(authRequestDTO.getEmail()));

        if (!passwordEncoder.matches(authRequestDTO.getPassword(), user.getPassword())) {
            throw new UserNotFoundException("Invalid credentials for user: " + authRequestDTO.getEmail());
        }

        return new AuthResponseDTO(user.getUserId());
    }
}
