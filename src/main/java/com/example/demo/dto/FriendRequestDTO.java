package com.example.demo.dto;


import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FriendRequestDTO {

    private Long id;

    private Long receiverId;

    private User sender;

    private User receiver;

    private String timeAgo;
}
