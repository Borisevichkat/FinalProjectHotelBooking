package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.UserDAO;
import by.borisevich.hotel.entity.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final String QUERY = "INSERT INTO User (firstname,lastname, email, password, isAdmin)"
            + " VALUES (?, ?, ?, ?, ?)";

    private static final String QUERY_GET_USER_BY_EMAIL = "SELECT firstname, lastname, email, password, isAdmin FROM user WHERE email = ?";

    private static final String QUERY_GET_USER_BY_ID = "SELECT firstname, lastname, email, password, isAdmin FROM user WHERE id = ?";

    public UserDAOImpl() {

    }

    @Override
    public void registration(User user) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setBoolean(5, user.isAdmin());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    user.setUserId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
    }


    @Override
    public User findById(int id) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_USER_BY_ID);
            ps.setInt(1, id);
            ps.executeQuery();
            pool.closeConnection(connection, ps);
        }catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return new User();
    }


    @Override
    public User findByEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_USER_BY_EMAIL);
            ps.setString(1, email);
            ps.executeQuery();
            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return new User();
    }


    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public void delete(User user) throws DAOException {

    }
}
