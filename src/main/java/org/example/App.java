package org.example;

import org.example.entity.User;
import org.example.service.UserService;

public class App {

    public static void main( String[] args ) {
        UserService userService = new UserService();

//        User user = userService.createUser("test firstname" , "test lastname" , "test username" , "test password");

        User foundUser = userService.findById(3);
        System.out.println(foundUser);
    }
}