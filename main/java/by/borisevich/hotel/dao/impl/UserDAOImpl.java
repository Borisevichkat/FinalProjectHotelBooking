package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.connectionPool.ConnectionPool;
import by.borisevich.hotel.connectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.UserDAO;
import by.borisevich.hotel.entity.User;

import java.sql.*;

public class UserDAOImpl implements UserDAO {

    private static final String QUERY = "INSERT INTO User (firstname,lastname, email, password, isAdmin)"
            + " VALUES (?, ?, ?, ?, ?)";

    private static final String QUERY_GET_USER_BY_EMAIL = "SELECT firstname, lastname, email, password, isAdmin FROM user WHERE email = ?";

    private static final String QUERY_GET_USER_BY_ID = "SELECT firstname, lastname, email, password, isAdmin FROM user WHERE id = ?";

    private static final String QUERY_AUTHORISATION = "SELECT * FROM user WHERE email = ? AND password = ?";
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
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new DAOException(e);
        } catch (SQLException e) {
            System.out.println(e.getStackTrace());
            e.printStackTrace();
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
        User user = new User();
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_USER_BY_ID);
            ps.setInt(1, id);

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            user.setUserId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setAdmin(resultSet.getBoolean(6));

        }catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return user;
    }

    @Override
    public User authorisation(String email, String password) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        User user = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_AUTHORISATION);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            resultSet.next();
            user = new User();
            user.setUserId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setAdmin(resultSet.getBoolean(6));

        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return user;
    }


    @Override
    public User findByEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        User user = new User();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_USER_BY_EMAIL);
            ps.setString(1, email);
            ps.executeQuery();

            ResultSet resultSet = ps.executeQuery();
            resultSet.next();

            user.setUserId(resultSet.getInt(1));
            user.setFirstName(resultSet.getString(2));
            user.setLastName(resultSet.getString(3));
            user.setEmail(resultSet.getString(4));
            user.setPassword(resultSet.getString(5));
            user.setAdmin(resultSet.getBoolean(6));

        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return user;
    }



    @Override
    public void update(User user) throws DAOException {

    }

    @Override
    public void delete(User user) throws DAOException {

    }
}
