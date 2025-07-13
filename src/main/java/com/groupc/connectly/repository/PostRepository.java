package com.groupc.connectly.repository;

import com.groupc.connectly.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.author.userId = :userId OR p.author IN " +
            "(SELECT f FROM User u JOIN u.friends f WHERE u.userId = :userId) " +
            "ORDER BY p.createdAt DESC")
    List<Post> findFeedPostsByUserId(Long userId);

}
