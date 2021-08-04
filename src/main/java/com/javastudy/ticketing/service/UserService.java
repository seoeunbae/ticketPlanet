package com.javastudy.ticketing.service;

import com.javastudy.ticketing.domain.User;
import com.javastudy.ticketing.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Transactional
public class UserService {
    //서비스에서 주로 사용하기위한 데이터가 레포지토리?
    @Qualifier("UserMemoryRepository")
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository){ this.userRepository = userRepository; }

    public User signUp(User user){
        validateDuplicatedUser(user);
        User result = userRepository.save(user);
        return result;
    }

    private void validateDuplicatedUser(User user){
        userRepository.findByName(user.getName())
                .ifPresent(me -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<User> getUserInfo(int user_id){
        return userRepository.findById(user_id);
    }

    public Optional<User> updateUserInfo(int user_id, User user){
        Optional<User> user_temp = userRepository.findById(user_id);

        validateDuplicatedUser(user);

        Optional<User> result = user_temp.map(selectedUser -> {
            selectedUser.setName(user.getName());
            selectedUser.setEmail(user.getEmail());
            selectedUser.setPassword(user.getPassword());
            User newUser = userRepository.update(user_id, selectedUser);
            return newUser;
        });
        System.out.println("update결과: "+ result);
        return result;
    }

    public void deleteUserInfo(int user_id){
        Optional<User> user = userRepository.findById(user_id);
        userRepository.delete(user_id);

    }


}
