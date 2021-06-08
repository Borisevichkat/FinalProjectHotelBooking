package by.borisevich.hotel.service;

import by.borisevich.hotel.entity.User;

public interface UserService {

    User authorisation(String email, String password);

    User registration(User user);
}
