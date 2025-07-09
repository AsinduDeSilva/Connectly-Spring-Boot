package com.example.demo.controller;

import com.example.demo.dto.FriendRequestDTO;
import com.example.demo.service.FriendRequestService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/friend-request")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/send")
    public String sendFriendRequest(@RequestBody FriendRequestDTO requestDTO) {
        friendRequestService.sendFriendRequest(requestDTO.getSenderId(), requestDTO.getReceiverId());
        return "Friend request sent!";
    }

}
