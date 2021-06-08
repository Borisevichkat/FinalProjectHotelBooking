package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.GuestDAO;
import by.borisevich.hotel.entity.Guest;

import java.sql.*;
import java.util.List;

public class GuestDaoImpl implements GuestDAO {

    private static final String query = "INSERT INTO Guest (passport, telephoneNumber, firstName, lastName, user)"
            + " VALUES (?, ?, ?, ?, ?)";

    List<Guest> allGuests;

    @Override
    public void addGuest(Guest guest) {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = by.borisevich.hotel.ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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

            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }

            } catch (SQLException e) {
                //
            }
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                //
            }
        }
    }

    @Override
    public List<Guest> getGuestsByIds(int[] guestsIds) {
        return null;
    }

    @Override
    public void update(Guest guest) {

    }

    @Override
    public void delete(Guest guest) {

    }

}
