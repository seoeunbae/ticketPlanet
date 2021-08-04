package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.User;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class jpaUserRepository implements UserRepository{

    public jpaUserRepository(EntityManager em) {

        this.em = em;
    }

    private final EntityManager em;


    @Override
    public User update(int id, User user) {
        String newName = user.getName();
        String newEmail = user.getEmail();
        String newPassword = user.getPassword();
        User result = em.createQuery("update User u set u.name = :newName, u.email= :newEmail, u.password = :newPassword where u.id= :id", User.class)
                .setParameter("newName",newName).setParameter("newEmail",newEmail).setParameter("newPassword",newPassword)
                .getSingleResult();
        return result;
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
    public Optional<User> findByName(String name) {
        List<User> result = em.createQuery("select u from User u where u.name = :name", User.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public void delete(int id) {
    User user = em.find(User.class,id);
    em.remove(user);
    }
}
