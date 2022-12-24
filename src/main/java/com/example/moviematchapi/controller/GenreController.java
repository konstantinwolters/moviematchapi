package com.example.moviematchapi.controller;

import com.example.moviematchapi.repository.GenreRepository;
import com.example.moviematchapi.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class GenreController {

    private final SessionRepository sessionRepository;
    private final GenreRepository genreRepository;

}
