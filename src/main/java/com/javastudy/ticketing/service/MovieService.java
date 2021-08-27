package com.javastudy.ticketing.service;

import com.javastudy.ticketing.domain.Movie;
import com.javastudy.ticketing.domain.User;
import com.javastudy.ticketing.repository.MovieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MovieService {

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private final MovieRepository movieRepository;

    private void validateDuplicatedMovie(Movie movie){
        movieRepository.findByTitle(movie.getTitle())
                .ifPresent(singlemovie -> {
                    throw new IllegalStateException("이미 존재하는 영화입니다.");
                });
    }

    public Movie createMovie(Movie movie){
        validateDuplicatedMovie(movie);
        Movie result =movieRepository.save(movie);
        return result;
    }

    public Optional<Movie> getMovieInfo(int movie_id){ return movieRepository.findById(movie_id); }

    public Optional<Movie> updateMovieInfo(int movie_id, Movie movie){
        Optional<Movie> movie_temp = movieRepository.findById(movie_id);

        validateDuplicatedMovie(movie);

        Optional<Movie> result = movie_temp.map(selectedMovie ->{
            selectedMovie.setTitle(movie.getTitle());
            selectedMovie.setPrice(movie.getPrice());
            selectedMovie.setDate(movie.getDate());
            Movie newMovie = movieRepository.update(movie_id, selectedMovie);
            return newMovie;
        });
        return result;
    }

    public List<Movie> getMovieAll(){ return movieRepository.getMovieAll(); }

    public void deleteMovieInfo(int movie_id){
        movieRepository.delete(movie_id);
    }

}
