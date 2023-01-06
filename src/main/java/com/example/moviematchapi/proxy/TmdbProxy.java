package com.example.moviematchapi.proxy;

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
}
