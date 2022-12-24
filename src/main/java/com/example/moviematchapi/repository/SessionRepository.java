package com.example.moviematchapi.repository;

import com.example.moviematchapi.model.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {

    Optional<Session> findByUuid(String hash);
}
