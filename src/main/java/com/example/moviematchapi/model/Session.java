package com.example.moviematchapi.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String uuid;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastAccess;

    @JsonManagedReference
    @OneToMany(mappedBy = "session",
    cascade = CascadeType.ALL,
    orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "session",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Genre> genres = new ArrayList<>();

    public void addUser(User user){
        users.add(user);
        user.setSession(this);
    }

    public void removeUser(User user){
        users.remove(user);
        user.setSession(null);
    }

    public void addGenre(Genre genre){
        genres.add(genre);
        genre.setSession(this);
    }

    public void removeGenre(Genre genre){
        genres.remove(genre);
        genre.setSession(null);
    }
}
