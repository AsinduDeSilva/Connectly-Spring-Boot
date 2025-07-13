package com.example.demo.service.impl;

import com.example.demo.dto.FriendRequestDTO;
import com.example.demo.model.FriendRequest;
import com.example.demo.model.User;
import com.example.demo.repository.FriendRequestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.FriendRequestService;
import com.example.demo.util.TimeUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {

    private final FriendRequestRepository friendRequestRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final TimeUtils timeUtils;

    public FriendRequestServiceImpl(FriendRequestRepository friendRequestRepository, UserRepository userRepository, AuthService authService, TimeUtils timeUtils) {
        this.friendRequestRepository = friendRequestRepository;
        this.userRepository = userRepository;
        this.authService = authService;
        this.timeUtils = timeUtils;
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
    public void acceptFriendRequest(Long requestId) {
        if(requestId == null) {
            throw new IllegalArgumentException("Request IDs must not be null");
        }

        User receiver = authService.getLoggedUser();

        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found from " + requestId));

        User sender = userRepository.findById(friendRequest.getSender().getUserId())
                .orElseThrow(() -> new IllegalArgumentException("Receiver not found with ID: " + friendRequest.getSender().getUserId()));

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

    @Override
    public void declineFriendRequest(Long requestId) {
        if(requestId == null) {
            throw new IllegalArgumentException("Request ID must not be null");
        }

        FriendRequest friendRequest = friendRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Friend request not found from " + requestId));

        friendRequestRepository.delete(friendRequest);
    }

    public List<FriendRequestDTO> getReceivedRequests(Long userId) {
        return friendRequestRepository.findFriendRequestsByReceiver_UserId(userId)
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<FriendRequestDTO> getSentRequests(Long userId) {
        return friendRequestRepository.findFriendRequestsBySender_UserId(userId)
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private FriendRequestDTO convertToDTO(FriendRequest friendRequest) {
        return new FriendRequestDTO(
                friendRequest.getId(),
                null,
                friendRequest.getSender(),
                friendRequest.getReceiver(),
                timeUtils.getTimeAgo(friendRequest.getSentAt())
        );
    }
}
