package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.Movie;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class jpaMovieRepository implements MovieRepository {

    public jpaMovieRepository(EntityManager em) {
        this.em = em;
    }

    private final EntityManager em;


    @Override
    public Movie update(int id, Movie movie) {
        String newTitle = movie.getTitle();
        int newPrice = movie.getPrice();
        String newDate = movie.getDate();
        Movie result = em.createQuery("update Movie m set m.title =: newTitle, m.price = :newPrice, m.date =:newDate where m.id = :id", Movie.class)
                .setParameter("newTitle",newTitle).setParameter("newPrice", newPrice).setParameter("newDate",newDate)
                .getSingleResult();
        return result;
    }

    @Override
    public Movie save(Movie movie) {
        em.persist(movie);
        return movie;
    }

    @Override
    public Optional<Movie> findById(int id) {
        Movie movie = em.find(Movie.class, id);
        return Optional.ofNullable(movie);
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        List<Movie> result = em.createQuery("select m from Movie m where m.title = :title", Movie.class)
                .setParameter("title",title)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Movie> getMovieAll() {
        return em.createQuery("select m from Movie m", Movie.class)
                .getResultList();
    }

    @Override
    public void delete(int id) {
        Movie movie = em.find(Movie.class , id);
        em.remove(movie);
    }

    @Override
    public Optional<Movie> findByTitleDate(String title, String date) {
        Movie result = em.createQuery("select m from Movie m where m.title = :title and m.date = :date", Movie.class)
                .setParameter("title",title).setParameter("date",date)
                .getSingleResult();
        return Optional.ofNullable(result);
    }
}
