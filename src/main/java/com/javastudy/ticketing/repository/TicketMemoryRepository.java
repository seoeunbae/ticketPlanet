package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.Ticket;
import com.javastudy.ticketing.domain.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository(value = "TicketMemoryRepository")
public class TicketMemoryRepository implements TicketRepository{
    private static Map<Integer, Ticket> tickets = new HashMap<>();
    private static int sequence = 0;


    @Override
    public Ticket update(int id, Ticket ticket) {
        Ticket singleTicket = tickets.get(id);
        singleTicket.setMovie(ticket.getMovie());
        singleTicket.setUser(ticket.getUser());
        return singleTicket;
    }

    @Override
    public Ticket save(Ticket ticket) {
        ticket.setId(++sequence);
        tickets.put(ticket.getId(),ticket);
        return ticket;
    }

    @Override
    public Optional<Ticket> findById(int id) {
        return Optional.ofNullable(tickets.get(id));
    }

    //    @Override
//    public Optional<Ticket[]> findByMovie_id(int movie_id){
//        return tickets.values().stream()
//                .filter(ticket -> ticket.getMovie().equals(movie_id))
//                .find
//    }
//
//    @Override
//    public Optional<Ticket> findByUser_id(int user_id) {
//        return tickets.values().stream()
//                .filter(ticket -> ticket.getUser().equals(user_id))
//                .findAny();
//    }

    @Override
    public void delete(int id) {
        tickets.remove(id);
    }
}
