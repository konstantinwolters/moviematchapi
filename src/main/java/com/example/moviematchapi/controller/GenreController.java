package com.example.moviematchapi.controller;

import com.example.moviematchapi.model.Genre;
import com.example.moviematchapi.proxy.TmdbProxy;
import com.example.moviematchapi.repository.GenreRepository;
import com.example.moviematchapi.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequiredArgsConstructor
public class GenreController {

    @Value("${tmdb.api.key}")
    private String apiKey;

    private final SessionRepository sessionRepository;
    private final GenreRepository genreRepository;
    private final TmdbProxy tmdbProxy;

    @GetMapping("/genre/genres")
    public List<Genre> getGenres(){
        return tmdbProxy.getGenres(apiKey).get("genres");
    }

}
