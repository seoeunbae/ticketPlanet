package com.javastudy.ticketing.controller;

import com.javastudy.ticketing.domain.Movie;
import com.javastudy.ticketing.domain.Ticket;
import com.javastudy.ticketing.domain.User;
import com.javastudy.ticketing.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TicketController {

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    private final TicketService ticketService;



    @PostMapping("ticket")
    public String createTicket(TicketForm form, Model model){
        Ticket ticket = new Ticket();
        Optional<Movie> movieObj = ticketService.convert2MovieObj(form.getMovie_id());
        Optional<User> userObj = ticketService.convert2UserObj(form.getUser_id());
        ticket.setUser(userObj.get());
        ticket.setMovie(movieObj.get());

        ticketService.createTicket(ticket);
        model.addAttribute("ticket", ticket);
        return "redirect:/";
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
