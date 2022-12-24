package com.example.moviematchapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewSessionRequestDTO {
    List<String> names;
    List<Integer> genres;
}
