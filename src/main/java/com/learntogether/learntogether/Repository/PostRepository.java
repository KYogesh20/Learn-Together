package com.learntogether.learntogether.Repository;

import com.learntogether.learntogether.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Integer> {
    Post findByPostId(Long postId);
}
