package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.GuestDAO;
import by.borisevich.hotel.entity.Guest;

import java.sql.*;
import java.util.List;

public class GuestDaoImpl implements GuestDAO {

    private static final String QUERY = "INSERT INTO Guest (passport, telephoneNumber, firstName, lastName, user)"
            + " VALUES (?, ?, ?, ?, ?)";

    List<Guest> allGuests;

    @Override
    public void addGuest(Guest guest) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, guest.getPassport());
            ps.setString(2, guest.getTelephoneNumber());
            ps.setString(3, guest.getFirstName());
            ps.setString(4, guest.getLastName());
            ps.setInt(5, guest.getUser());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating guest failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    guest.setGuestId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating guest failed, no ID obtained.");
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
    public List<Guest> getGuestsByIds(int[] guestsIds) throws DAOException {
        return null;
    }

    @Override
    public void update(Guest guest) throws DAOException{

    }

    @Override
    public void delete(Guest guest) {

    }

}
