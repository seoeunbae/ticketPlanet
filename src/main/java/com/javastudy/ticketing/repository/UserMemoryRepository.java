package com.javastudy.ticketing.repository;

import com.javastudy.ticketing.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Repository(value = "UserMemoryRepository")
public class UserMemoryRepository implements UserRepository{
    private static Map<Integer, User> users = new HashMap<>();
    private static int sequence = 0;

    @Override
    public User save(User user) {
        user.setId(++sequence);
        users.put(user.getId(),user);
        return user;
    }

    @Override
    public User update(int id,User user){
        User singleUser = users.get(id);
        singleUser.setName(user.getName());
        singleUser.setEmail(user.getEmail());
        singleUser.setPassword(user.getPassword());
        return singleUser;
    }

    @Override
    public Optional<User> findById(int id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Optional<User> findByName(String name) {
        return users.values().stream()
                .filter(user -> user.getName().equals(name))
                .findAny();
    }

    @Override
    public void delete(int id) {
        users.remove(id);
    }



}
