package com.example.moviematchapi.repository;

import com.example.moviematchapi.model.Like;
import com.example.moviematchapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LikeRepository extends JpaRepository<Like, Long> {

    @Query(value = "SELECT COUNT(*) FROM Session " +
            "JOIN Users ON Session.id=Users.session_id " +
            "JOIN Likes ON Users.id=Likes.user_id " +
            "WHERE Session.Id = :sessionId "+
            "AND Likes.tmdb_Movie_Id = :tmdbMovieId", nativeQuery = true)
    Integer getMatchedLikeCount(@Param("sessionId") String sessionId, @Param("tmdbMovieId") Integer tmdbMovieId);

    Boolean existsByTmdbMovieIdAndAndUser(Integer tmdbMovieId, User user);

}
