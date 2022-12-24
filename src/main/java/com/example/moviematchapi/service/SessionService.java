package com.example.moviematchapi.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SessionService {

    public String returnSessionUuid(){
        return UUID.randomUUID().toString();
    }
}
