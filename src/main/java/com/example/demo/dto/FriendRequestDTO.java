package com.example.demo.dto;


import com.example.demo.model.User;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendRequestDTO {

    @NotNull
    User sender;

    @NotNull
    User receiver;

    @NotNull
    LocalDateTime sentAt;
}
