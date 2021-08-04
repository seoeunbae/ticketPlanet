package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Primary
public interface UserRepository {
    User update(int id , User user);
    User save(User user);
    abstract Optional<User> findById(int id);
    Optional<User> findByName(String name);
    void delete(int id);
}
