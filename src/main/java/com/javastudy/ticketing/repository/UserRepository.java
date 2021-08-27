package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.User;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface UserRepository {
    @Modifying
    @Query("UPDATE User u SET u = :user WHERE u.id = :id")
    User update(int id , User user);
    Optional<User> findByEmailAndPassword(String inputEmail,String inputPassword);
    User save(User user);
    Optional<User> findById(int id);
    Optional<User> findByUsername(String name);
    void delete(int id);
//    @EntityGraph(attributePaths = "authorities")
//    Optional<org.springframework.security.core.userdetails.User> findOneWithAuthoritiesByUsername(String username);


}
