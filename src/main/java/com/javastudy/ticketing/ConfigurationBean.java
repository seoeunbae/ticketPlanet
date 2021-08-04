package com.javastudy.ticketing;

import com.javastudy.ticketing.repository.*;
import com.javastudy.ticketing.service.MovieService;
import com.javastudy.ticketing.service.TicketService;
import com.javastudy.ticketing.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class ConfigurationBean {

    private final DataSource dataSource;
    private EntityManager em;


    public ConfigurationBean(DataSource dataSource, EntityManager em){
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }
    @Bean
    public MovieService movieService(){
        return new MovieService(movieRepository());
    }
    @Bean
    public TicketService ticketService(){
        return new TicketService(ticketRepository(),userRepository(),movieRepository());
    }
    @Bean
    public UserRepository userRepository() {
        return new jpaUserRepository(em);
    }
    @Bean
    public MovieRepository movieRepository(){
        return new jpaMovieRepository(em);
    }
    @Bean
    public TicketRepository ticketRepository(){
        return new jpaTicketRepository(em);
    }


}
