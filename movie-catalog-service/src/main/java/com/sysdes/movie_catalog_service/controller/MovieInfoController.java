package com.sysdes.movie_catalog_service.controller;

import com.sysdes.movie_catalog_service.model.MovieInfo;
import com.sysdes.movie_catalog_service.model.MovieInfoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieInfoController {

    private MovieInfoRepository movieInfoRepository;

    private MovieInfoController(MovieInfoRepository repository){
        this.movieInfoRepository = repository;
    }
    @PostMapping("/movie-info/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> movielist) {
        return movieInfoRepository.saveAll(movielist);
    }

    @GetMapping("/movie-info/list")
    public List<MovieInfo> getAll(){
        return movieInfoRepository.findAll();
    }

    @GetMapping("/movie-info/find-path-by-id/{movieInfoId}")
    public String findPathById(@PathVariable Long movieInfoId){
        var videoInfoOptional = movieInfoRepository.findById(movieInfoId);
        return videoInfoOptional.map(movieInfo -> movieInfo.getPath()).orElse(null);
    }


}
