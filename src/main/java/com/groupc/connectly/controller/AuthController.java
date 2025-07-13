package com.groupc.connectly.controller;

import com.groupc.connectly.dto.AuthRequestDTO;
import com.groupc.connectly.dto.AuthResponseDTO;
import com.groupc.connectly.dto.CRUDResponseDTO;
import com.groupc.connectly.service.AuthService;
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
