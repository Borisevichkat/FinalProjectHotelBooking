package dao;

import dao.impl.UserDAOImpl;

public final class DAOProvider {

    private static final DAOProvider instance = new DAOProvider();

    private final UserDAO userDAO = new UserDAOImpl();

    private DAOProvider() {
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public static DAOProvider getInstance() {
        return instance;
    }
}
