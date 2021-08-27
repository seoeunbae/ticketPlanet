package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.Movie;
import com.javastudy.ticketing.domain.User;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Optional;
@Primary
public interface MovieRepository{
    Movie update(int id , Movie movie);
    Movie save(Movie movie);
    Optional<Movie> findById(int id);
    Optional<Movie> findByTitle(String title);
    List<Movie> getMovieAll();
    void delete(int id);
    Optional<Movie> findByTitleDate(String title, String date);
}
