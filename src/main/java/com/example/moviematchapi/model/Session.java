package com.example.moviematchapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastAccess;

    @JsonManagedReference
    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users = new ArrayList<>();


    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "genres_session", joinColumns = @JoinColumn(name = "session_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new HashSet<>();

    public void addUser(User user) {
        users.add(user);
        user.setSession(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.setSession(null);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getSessions().add(this);
    }

    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getSessions().remove(null);
    }
}
