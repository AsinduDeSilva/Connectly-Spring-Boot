package com.example.demo.service.impl;

import com.example.demo.model.FriendRequest;
import com.example.demo.model.User;
import com.example.demo.repository.FriendRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FriendRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository, UserRepository userRepository) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void sendFriendRequest(Long senderId, Long receiverId) {
        if(senderId == null || receiverId == null) {
            throw new IllegalArgumentException("Sender and receiver IDs must not be null");
        }
        if(senderId.equals(receiverId)) {
            throw new IllegalArgumentException("Sender and receiver cannot be the same");
        }
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new IllegalArgumentException("Sender not found with ID: " + senderId));

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found with ID: " + receiverId));

        Optional<FriendRequest> existingRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver);
        if(existingRequest.isPresent()) {
            throw new IllegalArgumentException("Friend request already exists from " + sender.getEmail() + " to " + receiver.getEmail());
        }

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(sender);
        friendRequest.setReceiver(receiver);
        friendRequest.setSentAt(LocalDateTime.now());
        friendRequestRepository.save(friendRequest);
        System.out.println("Friend request sent from " + sender.getEmail() + " to " + receiver.getEmail());
        
    }
}
