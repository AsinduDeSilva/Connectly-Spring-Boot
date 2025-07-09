package com.example.demo.controller;

import com.example.demo.dto.AuthRequestDTO;
import com.example.demo.dto.AuthResponseDTO;
import com.example.demo.dto.CRUDResponseDTO;
import com.example.demo.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("signin")
    public ResponseEntity<CRUDResponseDTO<AuthResponseDTO>> signIn(@RequestBody AuthRequestDTO authRequestDTO) {
        return ResponseEntity
                .accepted()
                .body(new CRUDResponseDTO<>(true, "Sign in successful", authService.signin(authRequestDTO)));
    }
}
