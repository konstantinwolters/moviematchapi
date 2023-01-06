package com.example.moviematchapi.controller;

import com.example.moviematchapi.dto.NewSessionRequestDTO;
import com.example.moviematchapi.exception.customException.NoSuchSessionException;
import com.example.moviematchapi.exception.customException.NoSuchUserInSessionException;
import com.example.moviematchapi.model.Genre;
import com.example.moviematchapi.model.Session;
import com.example.moviematchapi.model.User;
import com.example.moviematchapi.proxy.TmdbProxy;
import com.example.moviematchapi.repository.GenreRepository;
import com.example.moviematchapi.repository.SessionRepository;
import com.example.moviematchapi.service.SessionService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;
    private final SessionRepository sessionRepository;
    private final GenreRepository genreRepository;
    private final TmdbProxy tmdbProxy;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @GetMapping("/start")
    public String startSession() {
        List<Genre> genres = tmdbProxy.getGenres(apiKey).get("genres");

        //TODO: Find another way to initialize genres!
        genreRepository.saveAll(genres);

        return "ok";
    }

    @PostMapping("/session")
    public Session newSession(
            @RequestBody NewSessionRequestDTO newSessionRequest) {
        Session session = new Session();
        newSessionRequest.getNames()
                .forEach(name -> session.addUser(User.builder().name(name).build()));

        // TODO: FIX !!!!
        // if(newSessionRequest.getGenres() != null){
        // newSessionRequest.getGenres()
        // .forEach(genreId ->
        // session.addGenre(Genre.builder().tmdbGenreId(genreId).build()));
        // }
        session.setUuid(sessionService.returnSessionUuid());
        session.setLastAccess(LocalDate.now());
        sessionRepository.save(session);

        return session;
    }

    @GetMapping("/session/{sessionUuid}/{userId}")
    public Session retrieveSession(@PathVariable String sessionUuid, @PathVariable Long userId) {
        Session session = sessionRepository.findByUuid(sessionUuid)
                .orElseThrow(() -> new NoSuchSessionException("Session does not exist: " + sessionUuid));

        if (session.getUsers().stream().noneMatch(user -> user.getId().equals(userId))) {
            throw new NoSuchUserInSessionException(
                    "User Id '" + userId + "' does not exist in Session: " + sessionUuid);
        }
        return session;
    }
}
