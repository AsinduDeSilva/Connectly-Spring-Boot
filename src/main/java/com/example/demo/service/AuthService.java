package com.example.demo.service;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.model.User;

public interface AuthService {
    AuthResponseDTO signin(AuthRequestDTO authRequestDTO);
    User getLoggedUser();
}
