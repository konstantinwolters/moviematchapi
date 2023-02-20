package com.example.moviematchapi.proxy;

import com.example.moviematchapi.dto.ResultDto;
import com.example.moviematchapi.model.Genre;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;

@FeignClient(name = "TmdbProxy", url = "${name.service.url}")
public interface TmdbProxy {

    @GetMapping("/genre/movie/list?api_key={apiKey}&language=en-US")
    Map<String, List<Genre>> getGenres(@PathVariable String apiKey);


    @GetMapping("/discover/movie?api_key={apiKey}" +
            "&language=en-US" +
            "&sort_by=popularity.desc" +
            "&vote_count.gte=200" +
            "&vote_average.gte=5" +
            // "&primary_release_year=" + "{year}" +
            "&with_genres=" + "{genre}" +
            "&page={page}")
    ResultDto getMovies(@PathVariable String apiKey,
                        // @PathVariable String year,
                        @PathVariable String genre,
                        @PathVariable Integer page);

}
