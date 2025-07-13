package com.groupc.connectly.repository;

import com.groupc.connectly.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUsersByUserIdNot(Long userId);
    Optional<User> findByEmail(String username);


}
