package com.example.demo.repository;

import com.example.demo.model.FriendRequest;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
    Optional<FriendRequest> findBySenderAndReceiver(User sender, User receiver);
    List<FriendRequest> findFriendRequestsBySender_UserId(Long userId);
    List<FriendRequest> findFriendRequestsByReceiver_UserId(Long userId);
}
