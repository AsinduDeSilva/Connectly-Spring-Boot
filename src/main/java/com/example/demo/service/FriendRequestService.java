package com.example.demo.service;


public interface FriendRequestService {
    void sendFriendRequest(Long receiverId);

    void acceptFriendRequest(Long receiverId);

    void declineFriendRequest(Long receiverId);
}
