package com.example.demo.dto;

import com.example.demo.model.Post;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

    @NotNull
    private Long userId;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull
    private String password;

    @Email
    @NotNull
    private String email;

    private Set<UserDTO> friends;

    private Set<PostDTO> posts;


}
