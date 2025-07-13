package com.groupc.connectly.service;

import com.groupc.connectly.dto.AuthRequestDTO;
import com.groupc.connectly.dto.AuthResponseDTO;
import com.groupc.connectly.model.User;

public interface AuthService {
    AuthResponseDTO signin(AuthRequestDTO authRequestDTO);
    User getLoggedUser();
}
