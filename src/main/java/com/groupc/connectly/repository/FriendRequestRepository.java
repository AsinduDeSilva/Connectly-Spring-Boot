package com.groupc.connectly.repository;

import com.groupc.connectly.model.FriendRequest;
import com.groupc.connectly.model.User;
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
