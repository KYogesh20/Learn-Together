package com.learntogether.learntogether.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long poolId;

    private String poolName;
    private String poolDescription;
    @OneToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User PoolOwner;

    @OneToMany(mappedBy = "pool")
    @JsonBackReference
    List<Post> posts = new ArrayList<>();

    @ManyToMany
    @JoinColumn(name="userId")
    @JsonIgnore
    private List<User> usersJoined;
}
