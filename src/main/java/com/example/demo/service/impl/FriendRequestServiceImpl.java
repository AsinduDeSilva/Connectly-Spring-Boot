package com.example.demo.service.impl;

import com.example.demo.model.FriendRequest;
import com.example.demo.model.User;
import com.example.demo.repository.FriendRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.FriendRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final AuthService authService;

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository, UserRepository userRepository, AuthService authService) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public void sendFriendRequest(Long receiverId) {
        if(receiverId == null) {
            throw new IllegalArgumentException("Receiver ID must not be null");
        }

        User loggedUser = authService.getLoggedUser();

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found with ID: " + receiverId));

        Optional<FriendRequest> existingRequest = friendRequestRepository.findBySenderAndReceiver(loggedUser, receiver);
        if(existingRequest.isPresent()) {
            throw new IllegalArgumentException("Friend request already exists from " + loggedUser.getEmail() + " to " + receiver.getEmail());
        }

        FriendRequest friendRequest = new FriendRequest();
        friendRequest.setSender(loggedUser);
        friendRequest.setReceiver(receiver);
        friendRequest.setSentAt(LocalDateTime.now());
        friendRequestRepository.save(friendRequest);

    }

    @Override
    public void acceptFriendRequest(Long receiverId) {
        if(receiverId == null) {
            throw new IllegalArgumentException("Receiver IDs must not be null");
        }

        User loggedUser = authService.getLoggedUser();

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found with ID: " + receiverId));

        FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(loggedUser, receiver)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found from " + loggedUser.getEmail() + " to " + receiver.getEmail()));

        Set<User> senderFriends = new HashSet<>(loggedUser.getFriends());
        Set<User> receiverFriends = new HashSet<>(receiver.getFriends());

        senderFriends.add(receiver);
        receiverFriends.add(loggedUser);

        loggedUser.setFriends(senderFriends);
        receiver.setFriends(receiverFriends);

        userRepository.save(loggedUser);
        userRepository.save(receiver);

        friendRequestRepository.delete(friendRequest);
    }

    @Override
    public void declineFriendRequest(Long receiverId) {
        if(receiverId == null) {
            throw new IllegalArgumentException("Receiver ID must not be null");
        }

        User loggedUser = authService.getLoggedUser();

        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found with ID: " + receiverId));

        FriendRequest friendRequest = friendRequestRepository.findBySenderAndReceiver(loggedUser, receiver)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found from " + loggedUser.getEmail() + " to " + receiver.getEmail()));

        friendRequestRepository.delete(friendRequest);
    }
}
