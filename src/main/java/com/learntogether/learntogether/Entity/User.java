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

    private int ratings = 0;

    private int noOfPostsReported = 0;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @ManyToMany(mappedBy = "usersJoined", cascade = CascadeType.ALL)
    List<Pool> poolsJoined = new ArrayList<>();

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    @JsonManagedReference
    List<Post> postsCreated = new ArrayList<>();

    @ManyToMany(mappedBy = "usersBookmarked")
    List<Post> bookmarkedPosts = new ArrayList<>();


    @ManyToMany
    @JoinColumn(name = "postId")
    @JsonIgnore
    private List<Post> postsUpvoted;
}