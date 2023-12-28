package com.learntogether.learntogether.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learntogether.learntogether.Enum.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String firstname;
    private String lastname;
    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    List<Long> poolsJoined = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Post> postsCreated = new ArrayList<>();
}