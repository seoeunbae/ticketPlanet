package com.javastudy.ticketing.controller;

import com.javastudy.ticketing.domain.Movie;
import com.javastudy.ticketing.domain.Ticket;
import com.javastudy.ticketing.domain.User;
import com.javastudy.ticketing.repository.MovieRepository;
import com.javastudy.ticketing.repository.UserRepository;
import com.javastudy.ticketing.service.MovieService;
import com.javastudy.ticketing.service.TicketService;
import com.javastudy.ticketing.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TicketController {
    public TicketController(TicketService ticketService, MovieService movieService) {
        this.ticketService = ticketService;
        this.movieService = movieService;
    }
    private final TicketService ticketService;
    private final MovieService movieService;

    @GetMapping("/ticketingform")
    public String ticketingForm(Model model){
        List<Movie> movies =   movieService.getMovieAll();
        model.addAttribute("movies",movies);
        return "ticket/ticketingForm";
    }


    @PostMapping("ticket")
    public String createTicket(createTicketForm form, Model model) throws Exception{
        try {
            Ticket ticket = ticketService.createTicket(form.getUsername(), form.getTitle(), form.getDate());
            ticketService.createTicket(ticket);
            model.addAttribute("ticket", ticket);
            return "redirect:/";
        } catch (Exception e) {
            model.addAttribute("e",e);
            return "error";
//            throw new IllegalStateException("정확한 유저,영화 정보를 입력해주세요.");
        }

    }

    @GetMapping("ticket")
    public String getTicketInfo(@RequestParam("ticket_id") int ticket_id, Model model){
        Optional<Ticket> ticket = ticketService.getTicketInfo(ticket_id);
        ticket.ifPresent(singleTicket -> model.addAttribute("ticket",singleTicket));
        return "ticket/ticketInfo";
    }

    @PatchMapping("ticket")
    @ResponseBody
    public Optional<Ticket> updateTicketInfo(@RequestParam("ticket_id") int ticket_id, @RequestBody Ticket ticket){
        return ticketService.updateTicketInfo(ticket_id, ticket);
    }

    @DeleteMapping("ticket")
    public String deleteTicketInfo(@RequestParam("ticket_id") int ticket_id){
        ticketService.deleteTicketInfo(ticket_id);
        return "redirect:/";
    }
}
