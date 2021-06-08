package by.borisevich.hotel.service.impl;

import by.borisevich.hotel.entity.User;
import by.borisevich.hotel.service.UserService;

public class UserServiceImpl implements UserService {

    public User authorisation(String email, String password) {
        return new User();
    }

    public User registration(User user) {
        return new User();
    }
}
