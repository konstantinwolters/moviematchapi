package com.example.moviematchapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "Users")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Session session;

    @JsonManagedReference
    @OneToMany(mappedBy = "user",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

    public void addLike(Like like){
        likes.add(like);
        like.setUser(this);
    }

    public void removeLike(Like like){
        likes.remove(like);
        like.setUser(null);
    }
}
