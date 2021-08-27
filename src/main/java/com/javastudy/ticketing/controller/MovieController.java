
package com.javastudy.ticketing.controller;

import com.javastudy.ticketing.domain.Movie;
import com.javastudy.ticketing.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {
    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    private final MovieService movieService;

    @GetMapping("createmovieform")
    public String createMovieForm(){
        return "movie/createMovieForm";
    }

    @PostMapping("movie")
    public String createMovie(MovieForm form, Model model){//Movieform의 각 속성에 html의 name에 맞는 속성들이 넣어져서 form이 생성된다.
        Movie movie = new Movie();
        movie.setTitle(form.getTitle());
        movie.setPrice(form.getPrice());
        movie.setDate(form.getDate());
        Movie result = movieService.createMovie(movie);

        model.addAttribute("movie",result);
        return "movie/movieInfo";
    }

    @GetMapping("movie")
    public String getMovieInfo(@RequestParam("movie_id") int movie_id, Model model){
        Optional<Movie> movie = movieService.getMovieInfo(movie_id);
        movie.ifPresent(singleMovie -> model.addAttribute("movie",singleMovie));
        return "movie/movieInfo";
    }

    @PatchMapping("movie")
    @ResponseBody
    public Optional<Movie> updateMovieInfo(@RequestParam("movie_id") int movie_id, @RequestBody Movie movie){
        return movieService.updateMovieInfo(movie_id, movie);
    }

    @DeleteMapping("movie")
    public String deleteMovieInfo(@RequestParam("movie_id") int movie_id){
        movieService.deleteMovieInfo(movie_id);
        return "redirect:/";
    }

    @GetMapping("movies")
    public String getMovieAll(Model model){
        List<Movie> movies = movieService.getMovieAll();
        model.addAttribute("movies", movies);
        return "movie/movieList";
    }
}
