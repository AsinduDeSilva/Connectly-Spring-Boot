package com.groupc.connectly.service;


import com.groupc.connectly.dto.FriendRequestDTO;

import java.util.List;

public interface FriendRequestService {
    void sendFriendRequest(Long receiverId);

    void acceptFriendRequest(Long requestId);

    void declineFriendRequest(Long requestId);

    List<FriendRequestDTO> getReceivedRequests(Long userId);

    List<FriendRequestDTO> getSentRequests(Long userId);
}
