package com.example.demo.service.impl;

import com.example.demo.repository.FriendRequestRepository;
import com.example.demo.service.FriendRequestService;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository) {
        this.friendRequestRepository = friendRequestRepository;
    }
}
