package com.sysdes.movie_movie_streaming_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MovieStreamController {

    public static final String VIDEO_DIR = "/Users/janani/Documents/GitHub/VideoStramingApp/video/";
    public static final Logger log = Logger.getLogger(MovieStreamController.class.getName());
    @Autowired
    public MovieCatalogService movieCatalogService;
    @GetMapping("/stream/{videoPath}")
    public ResponseEntity<InputStreamResource> streamVideo(@PathVariable String videoPath) throws FileNotFoundException {
        File file = new File(VIDEO_DIR+videoPath);
        if (file.exists()){
            InputStreamResource inputStreamResource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok().
                    contentType(MediaType.parseMediaType("video/mp4")).
                    body(inputStreamResource);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/stream/with-id/{videoInfoId}")
    public ResponseEntity<InputStreamResource> streamVideobyId(@PathVariable Long videoInfoId) throws FileNotFoundException {
       String moviepath =  movieCatalogService.getMoviePath(videoInfoId);
       log.log(Level.INFO,"Resolved movie path = {0}",moviepath);
       return streamVideo(moviepath);
    }
}
