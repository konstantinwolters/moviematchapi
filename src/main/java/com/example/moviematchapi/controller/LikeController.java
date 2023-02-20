package com.example.moviematchapi.controller;

import com.example.moviematchapi.model.Like;
import com.example.moviematchapi.repository.LikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
public class LikeController {

    private final LikeRepository likeRepository;

    @PostMapping("/like")
    public void addLike(@RequestBody Like like){

        if(!likeRepository.existsByTmdbMovieIdAndAndUser(like.getTmdbMovieId(), like.getUser())) {
            likeRepository.save(like);
        }
    }

    @GetMapping("/like/matchedLikeCount/{sessionId}/{tmdbMovieId}")
    public Integer getMatchedLikeCount(@PathVariable String sessionId, @PathVariable Integer tmdbMovieId){

        return likeRepository.getMatchedLikeCount(sessionId, tmdbMovieId);
    }
}

