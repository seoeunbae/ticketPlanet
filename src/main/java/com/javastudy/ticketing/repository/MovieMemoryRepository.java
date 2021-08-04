package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.Movie;
import com.javastudy.ticketing.domain.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository(value = "MovieMemoryRepository")
public class MovieMemoryRepository implements MovieRepository {
    private static Map<Integer, Movie> movies = new HashMap<>();
    private static int sequence = 0;

    @Override
    public Movie update(int id, Movie movie) {
        Movie singleMovie = movies.get(id);
        singleMovie.setTitle(movie.getTitle());
        singleMovie.setPrice(movie.getPrice());
        singleMovie.setDate(movie.getDate());
        return singleMovie;
    }

    @Override
    public Movie save(Movie movie) {
        movie.setId(++sequence);
        movies.put(movie.getId(), movie);
        return null;
    }

    @Override
    public Optional<Movie> findById(int id) {

        return Optional.ofNullable(movies.get(id));
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movies.values().stream()
                .filter(movie -> movie.getTitle().equals(title))
                .findAny();
    }

    @Override
    public List<Movie> getMovieAll() {
        return new ArrayList<>(movies.values());
    }

    @Override
    public void delete(int id) { movies.remove(id); }
}
