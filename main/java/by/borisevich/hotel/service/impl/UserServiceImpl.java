package by.borisevich.hotel.service.impl;

import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.entity.User;
import by.borisevich.hotel.service.UserService;
import by.borisevich.hotel.dao.impl.UserDAOImpl;
import by.borisevich.hotel.dao.UserDAO;

public class UserServiceImpl implements UserService {

    public User authorisation(String email, String password) throws DAOException {
        UserDAO userDAO = new UserDAOImpl();
        User user = userDAO.authorisation(email, password);
        return user;
    }

    public User registration(User user) throws DAOException {
        UserDAO userDAO = new UserDAOImpl();
        userDAO.registration(user);
        return user;
    }
}
