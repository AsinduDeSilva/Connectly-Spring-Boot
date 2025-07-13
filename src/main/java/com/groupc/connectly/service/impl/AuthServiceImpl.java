package com.groupc.connectly.service.impl;

import com.groupc.connectly.dto.AuthRequestDTO;
import com.groupc.connectly.dto.AuthResponseDTO;
import com.groupc.connectly.exception.UserNotFoundException;
import com.groupc.connectly.model.User;
import com.groupc.connectly.repository.UserRepository;
import com.groupc.connectly.service.AuthService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Override
    public User getLoggedUser() {
        UserDetails userdetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userdetails.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + userdetails.getUsername()));
    }
}
