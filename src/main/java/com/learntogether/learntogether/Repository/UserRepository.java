package com.learntogether.learntogether.Repository;

import com.learntogether.learntogether.Entity.User;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);
}
