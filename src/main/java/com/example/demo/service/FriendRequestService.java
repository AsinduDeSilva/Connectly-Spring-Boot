package com.example.demo.service;

import jakarta.validation.constraints.NotNull;

public interface FriendRequestService {
    void sendFriendRequest(Long senderId, Long receiverId);

    void acceptFriendRequest(@NotNull Long senderId, @NotNull Long receiverId);
}
