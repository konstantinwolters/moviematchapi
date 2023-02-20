package com.example.moviematchapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResultDto {
    private Integer page;
    private List<MovieDto> results;
    private Integer total_results;
    private Integer total_pages;

}
