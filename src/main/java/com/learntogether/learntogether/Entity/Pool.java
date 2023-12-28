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

    private Long ownerId;
    private String poolName;
    private String poolDescription;

    @OneToMany(mappedBy = "pool")
    List<Post> posts = new ArrayList<>();

    List<Long> usersJoined = new ArrayList<>();
}
