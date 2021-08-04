package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.Movie;
import com.javastudy.ticketing.domain.Ticket;
import com.javastudy.ticketing.domain.User;

import javax.persistence.EntityManager;
import java.util.Optional;

public class jpaTicketRepository implements TicketRepository{
    public jpaTicketRepository(EntityManager em) {
        this.em = em;
    }

    private final EntityManager em;


    @Override
    public Ticket update(int id, Ticket ticket) {
        Movie newMovie = ticket.getMovie();
        User newUser = ticket.getUser();
        Ticket result = em.createQuery("update Ticket t set t.movie = :newMovie, t.user =:newUser where t.id= :id", Ticket.class)
                .setParameter("newMovie",newMovie).setParameter("newUser",newUser)
                .getSingleResult();
        return result;
    }

    @Override
    public Ticket save(Ticket ticket) {
        em.persist(ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> findById(int id) {
        Ticket ticket = em.find(Ticket.class, id);
        return Optional.ofNullable(ticket);
    }

    @Override
    public void delete(int id) {
        Ticket ticket = em.find(Ticket.class, id);
        em.remove(ticket);
    }
}
