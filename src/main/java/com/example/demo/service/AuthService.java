package com.example.demo.service;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponseDTO;

public interface AuthService {
    AuthResponseDTO signin(AuthRequestDTO authRequestDTO);
}
