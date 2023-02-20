package com.example.moviematchapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class NewSessionRequestDto {
    List<String> names;
    List<String> genres;
}
