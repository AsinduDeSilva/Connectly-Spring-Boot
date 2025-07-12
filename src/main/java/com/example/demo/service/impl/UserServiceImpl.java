package com.example.demo.service.impl;

import com.example.demo.dto.DetailedUserDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.FriendRequest;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UserServiceImpl(
            UserRepository userRepository,
            ModelMapper modelMapper,
            PasswordEncoder passwordEncoder,
            AuthService authService
    ) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User newUser = modelMapper.map(userDTO, User.class);
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return modelMapper.map(userRepository.save(newUser), UserDTO.class);
    }
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(
                user -> modelMapper.map(user, UserDTO.class)
        ).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long userId) {
        if (userRepository.findById(userId).isPresent()){
            return modelMapper.map(userRepository.findById(userId),UserDTO.class);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public List<DetailedUserDTO> getUsersList() {
        User loggedUser = authService.getLoggedUser();

        List<User> users = userRepository.findUsersByUserIdNot(loggedUser.getUserId());

        Set<User> friends = loggedUser.getFriends();
        Set<FriendRequest> sentRequests = loggedUser.getSentRequests();
        Set<FriendRequest> receivedRequests = loggedUser.getReceivedRequests();

        return users.stream().map(user -> {
            DetailedUserDTO detailedUserDTO = modelMapper.map(user, DetailedUserDTO.class);

            detailedUserDTO.setFriend(friends.contains(user));

            boolean hasSent = sentRequests.stream()
                    .anyMatch(req -> req.getReceiver().getUserId().equals(user.getUserId()));
            detailedUserDTO.setHasSentFriendRequest(hasSent);

            boolean hasReceived = receivedRequests.stream()
                    .anyMatch(req -> req.getSender().getUserId().equals(user.getUserId()));
            detailedUserDTO.setHasReceivedFriendRequest(hasReceived);

            return detailedUserDTO;
        }).collect(Collectors.toList());
    }
}