package com.example.demo.controller;

import com.example.demo.dto.FriendRequestDTO;
import com.example.demo.service.FriendRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/friend-request")
public class FriendRequestController {

    private final FriendRequestService friendRequestService;

    public FriendRequestController(FriendRequestService friendRequestService) {
        this.friendRequestService = friendRequestService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> sendFriendRequest(@RequestBody FriendRequestDTO requestDTO) {
        friendRequestService.sendFriendRequest(requestDTO.getSenderId(), requestDTO.getReceiverId());
        return ResponseEntity.ok("Friend request sent successfully");
    }

    @PostMapping("/accept")
    public ResponseEntity<String> acceptFriendRequest(@RequestBody FriendRequestDTO requestDTO) {
        friendRequestService.acceptFriendRequest(requestDTO.getSenderId(), requestDTO.getReceiverId());
        return ResponseEntity.ok("Friend request accepted successfully");
    }

    @PostMapping("/decline")
    public ResponseEntity<String> declineFriendRequest(@RequestBody FriendRequestDTO requestDTO) {
        friendRequestService.declineFriendRequest(requestDTO.getSenderId(), requestDTO.getReceiverId());
        return ResponseEntity.ok("Friend request declined successfully");
    }
}
