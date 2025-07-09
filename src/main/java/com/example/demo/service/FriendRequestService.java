package com.example.demo.service;

import org.springframework.http.ResponseEntity;

public interface FriendRequestService {
    ResponseEntity<String> sendFriendRequest(Long senderId, Long receiverId);
}
