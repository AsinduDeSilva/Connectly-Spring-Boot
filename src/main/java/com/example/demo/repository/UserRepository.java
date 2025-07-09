package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> getFriendsByUserId(Long userId);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.friends f LEFT JOIN FETCH u.posts WHERE u.id = :userId")
    Optional<User> findUserWithFriendsAndPosts(@Param("userId") Long userId);

    Optional<User> findByUserId(Long id);
}
