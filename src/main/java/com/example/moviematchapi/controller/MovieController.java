package com.example.moviematchapi.controller;

import com.example.moviematchapi.dto.MovieDto;
import com.example.moviematchapi.proxy.TmdbProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final TmdbProxy tmdbProxy;

    @Value("${tmdb.api.key}")
    private String apiKey;

    @GetMapping("/movie/{sessionId}")
    public List<MovieDto> getMovies (@RequestParam(name = "page") Integer page,
                                     @RequestParam(name = "genres") String genres){

        List<MovieDto> result = tmdbProxy.getMovies(apiKey, genres,page).getResults();
        System.out.println("Results: " + result.size() + " with request parameter(genre): " + genres + " page: " + page);
        return result;
    }
}
