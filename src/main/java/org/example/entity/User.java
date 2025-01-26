package org.example.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private Integer passportId;

    public User(String firstname , String lastname , String username , String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public User(Integer id , String firstname , String lastname , String username , String password , Integer passportId) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.passportId = passportId;
    }
}
