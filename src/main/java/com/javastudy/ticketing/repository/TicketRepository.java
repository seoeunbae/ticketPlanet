package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.Ticket;
import com.javastudy.ticketing.domain.User;

import java.util.Optional;

public interface TicketRepository {
    Ticket update(int id , Ticket ticket);
    Ticket save(Ticket ticket);
    Optional<Ticket> findById(int id);
//    Optional<Ticket> findByMovie_id(int movie_id);
//    Optional<Ticket> findByUser_id(int user_id);
    void delete(int id);
}
