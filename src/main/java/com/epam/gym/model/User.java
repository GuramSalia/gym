package com.epam.gym.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Slf4j
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private Boolean isActive;
}
