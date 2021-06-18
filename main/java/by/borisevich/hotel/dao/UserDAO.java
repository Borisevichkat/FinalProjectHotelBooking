package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.User;

public interface UserDAO {

    User findById(int id) throws DAOException;

    User authorisation(String email, String password) throws DAOException;

    User findByEmail(String email) throws DAOException;

    void update(User user) throws DAOException;

    void delete(User user) throws DAOException;

    void registration(User user) throws DAOException;
}
