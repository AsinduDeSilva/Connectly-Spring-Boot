package com.example.demo.service.impl;

import com.example.demo.model.FriendRequest;
import com.example.demo.model.User;
import com.example.demo.repository.FriendRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.FriendRequestService;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    }

    @Override
    public void acceptFriendRequest(Long senderId, Long receiverId) {
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

        FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(sender, receiver)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found from " + sender.getEmail() + " to " + receiver.getEmail()));

//        sender.getFriends().add(receiver);
//        receiver.getFriends().add(sender);

        Set<User> senderFriends = new HashSet<>(sender.getFriends());
        Set<User> receiverFriends = new HashSet<>(receiver.getFriends());

        senderFriends.add(receiver);
        receiverFriends.add(sender);

        sender.setFriends(senderFriends);
        receiver.setFriends(receiverFriends);

        userRepository.save(sender);
        userRepository.save(receiver);

        friendRequestRepository.delete(friendRequest);
    }
}
