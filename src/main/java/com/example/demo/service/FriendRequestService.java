package com.example.demo.service;


import com.example.demo.dto.FriendRequestDTO;

import java.util.List;

public interface FriendRequestService {
    void sendFriendRequest(Long receiverId);

    void acceptFriendRequest(Long requestId);

    void declineFriendRequest(Long requestId);

    List<FriendRequestDTO> getReceivedRequests(Long userId);

    List<FriendRequestDTO> getSentRequests(Long userId);
}
