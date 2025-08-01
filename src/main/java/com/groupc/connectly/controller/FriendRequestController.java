package com.groupc.connectly.controller;

import com.groupc.connectly.dto.CRUDResponseDTO;
import com.groupc.connectly.dto.FriendRequestDTO;
import com.groupc.connectly.service.FriendRequestService;
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
    public ResponseEntity<CRUDResponseDTO<Void>> sendFriendRequest(@RequestBody FriendRequestDTO requestDTO) {
        friendRequestService.sendFriendRequest(requestDTO.getReceiverId());
        return ResponseEntity.ok(new CRUDResponseDTO<>(true, "Friend request sent successfully", null));
    }

    @PostMapping("/accept")
    public ResponseEntity<CRUDResponseDTO<Void>> acceptFriendRequest(@RequestBody FriendRequestDTO requestDTO) {
        friendRequestService.acceptFriendRequest(requestDTO.getId());
        return ResponseEntity.ok(new CRUDResponseDTO<>(true, "Friend request accepted successfully", null));
    }

    @PostMapping("/decline")
    public ResponseEntity<CRUDResponseDTO<String>> declineFriendRequest(@RequestBody FriendRequestDTO requestDTO) {
        friendRequestService.declineFriendRequest(requestDTO.getId());
        return ResponseEntity.ok(new CRUDResponseDTO<>(true,"Friend request declined successfully",null));
    }
}
