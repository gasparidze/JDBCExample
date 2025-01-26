package org.example.service;

import org.example.entity.User;
import org.example.repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();
    public User createUser(String firstname, String lastname, String username, String password){
        User user = new User(firstname, lastname, username, password);
        return userRepository.insert(user);
    }

    public User findById(Integer userId){
        return userRepository.findById(userId);
    }
}
