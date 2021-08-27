package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


public class jpaUserRepository implements UserRepository {

    public jpaUserRepository(EntityManager em) {
        this.em = em;
    }

    private final EntityManager em;


    @Override
    public User update(int id, User user) {
        String newName = user.getUsername();
        String newEmail = user.getEmail();
        String newPassword = user.getPassword();
        User result = em.createQuery("update User u set u.name = :newName, u.email= :newEmail, u.password = :newPassword where u.id= :id", User.class)
                .setParameter("newName",newName).setParameter("newEmail",newEmail).setParameter("newPassword",newPassword)
                .getSingleResult();
        return result;
    }

    @Override
    public Optional<User> findByEmailAndPassword(String inputEmail, String inputPassword) {
        User result = em.createQuery("select u from User u where u.email = :inputEmail and u.password =:inputPassword",User.class)
                .setParameter("inputEmail", inputEmail).setParameter("inputPassword", inputPassword)
                .getSingleResult();
        return Optional.ofNullable(result);
    }

    @Override
    public User save(User user) {
        em.persist(user);
        return user;
    }

    @Override
    public Optional<User> findById(int id) {
        User user = em.find(User.class, id);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        List<User> result = em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public void delete(int id) {
    User user = em.find(User.class,id);
    em.remove(user);
    }


}
