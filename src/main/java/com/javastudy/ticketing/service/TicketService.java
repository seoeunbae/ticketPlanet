package com.javastudy.ticketing.service;

import com.javastudy.ticketing.domain.Movie;
import com.javastudy.ticketing.domain.Ticket;
import com.javastudy.ticketing.domain.User;
import com.javastudy.ticketing.repository.MovieRepository;
import com.javastudy.ticketing.repository.TicketRepository;
import com.javastudy.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
public class TicketService {

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository, MovieRepository movieRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final MovieRepository movieRepository;




//    private void validateDuplicateTicket(Ticket ticket){
//
//    }

    public Optional<User> convert2UserObj(int user_id){
        Optional<User> result = userRepository.findById(user_id);
        return result;
    }

    public Optional<Movie> convert2MovieObj(int movie_id){
        Optional<Movie> result = movieRepository.findById(movie_id);
        return result;
    }

    public Ticket createTicket(Ticket ticket) {
        Ticket result = ticketRepository.save(ticket);
        return result;
    }

    public Optional<Ticket> getTicketInfo(int id) { return ticketRepository.findById(id);}

    public Optional<Ticket> updateTicketInfo(int id, Ticket ticket){
        Optional<Ticket> ticket_temp = ticketRepository.findById(id);

        Optional<Ticket> result = ticket_temp.map(selectedTicket->{
            selectedTicket.setUser(ticket.getUser());
            selectedTicket.setMovie(ticket.getMovie());
            Ticket newTicket = ticketRepository.update(id, selectedTicket);
            return newTicket;
        });
        return result;
    }

    public void deleteTicketInfo(int id){ ticketRepository.delete(id); }

}
