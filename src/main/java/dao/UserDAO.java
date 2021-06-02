package dao;

import entity.User;

public interface UserDAO {

    User findById(int id);
    User findByEmail(String email);

    void update(User user);
    void delete(User user);

    boolean registration (User user);
}
