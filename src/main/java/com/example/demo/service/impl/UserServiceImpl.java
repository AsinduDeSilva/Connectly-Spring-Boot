package com.example.demo.service.impl;

import com.example.demo.dto.UserDTO;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        return modelMapper.map(userRepository.save(modelMapper.map(userDTO, User.class)),UserDTO.class);
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

    public List<UserDTO> getUsersList(Long userId) {
//        List<User> users = userRepository.findAll();
//        users.forEach(user -> {
//            if (user.getUserId().equals(userId)) {
//                users.remove(user);
//            }
//        });
//        return users.stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());

        return userRepository.findUsersByUserIdNot(userId).stream()
                .map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
    }
}