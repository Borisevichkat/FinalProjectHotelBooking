package by.borisevich.hotel.service;

import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.entity.User;

public interface UserService {

    User authorisation(String email, String password) throws DAOException;

    User registration(User user) throws DAOException;
}
