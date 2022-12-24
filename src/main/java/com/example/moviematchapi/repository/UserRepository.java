package com.example.moviematchapi.repository;

import com.example.moviematchapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
