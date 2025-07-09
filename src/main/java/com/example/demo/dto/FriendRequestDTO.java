package com.example.demo.dto;


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
    private Long senderId;

    @NotNull
    private Long receiverId;

}
