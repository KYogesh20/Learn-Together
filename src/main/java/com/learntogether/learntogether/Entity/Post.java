package com.learntogether.learntogether.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    private String content;

    @OneToOne
    @JoinColumn(name="userId")
    @JsonIgnore
    User author;

    @ManyToOne
    @JoinColumn(name="poolId")
    @JsonIgnore
    Pool pool;

    @ManyToMany
    @JoinColumn(name="userId")
    @JsonIgnore
    private List<User> usersBookmarked;

    @CreationTimestamp
    Date time;

    @ManyToMany(mappedBy = "postsUpvoted")
    List<User> usersUpvoted = new ArrayList<>();
}
