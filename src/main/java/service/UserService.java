package service;

import entity.User;

public interface UserService {

    User authorisation(String email, String password);
    User registration(User user);
}
