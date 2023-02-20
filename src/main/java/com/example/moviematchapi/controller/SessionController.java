package com.example.moviematchapi.controller;

import com.example.moviematchapi.dto.NewSessionRequestDto;
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
            @RequestBody NewSessionRequestDto newSessionRequest) {
        Session session = new Session();
        newSessionRequest.getNames()
                .forEach(name -> session.addUser(User.builder().name(name).build()));

        if(newSessionRequest.getGenres() != null){
            newSessionRequest.getGenres()
                    .forEach(Genre -> session.addGenre(genreRepository.findById(Integer.valueOf(Genre)).get()));
        }
        session.setLastAccess(LocalDate.now());
        return sessionRepository.save(session);

    }

    @GetMapping("/session/{sessionId}/{userId}")
    public Session retrieveSession(@PathVariable String sessionId, @PathVariable Long userId) {
        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new NoSuchSessionException("Session does not exist: " + sessionId));

        if (session.getUsers().stream().noneMatch(user -> user.getId().equals(userId))) {
            throw new NoSuchUserInSessionException(
                    "User Id '" + userId + "' does not exist in Session: " + sessionId);
        }
        return session;
    }
}
