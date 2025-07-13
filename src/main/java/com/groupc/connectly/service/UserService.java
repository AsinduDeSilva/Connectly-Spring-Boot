package com.groupc.connectly.service;

import com.groupc.connectly.dto.DetailedUserDTO;
import com.groupc.connectly.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long userId);
    List<DetailedUserDTO> getUsersList();
}
