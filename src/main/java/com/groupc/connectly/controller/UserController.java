package com.groupc.connectly.controller;

import com.groupc.connectly.dto.CRUDResponseDTO;
import com.groupc.connectly.dto.DetailedUserDTO;
import com.groupc.connectly.dto.UserDTO;
import com.groupc.connectly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CRUDResponseDTO<UserDTO>> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new CRUDResponseDTO<>(true, "User created successfully", userService.createUser(userDTO)));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/list")
    public ResponseEntity<List<DetailedUserDTO>> getUsersList() {
        return ResponseEntity.ok(userService.getUsersList());
    }
}
