package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.connectionPool.ConnectionPool;
import by.borisevich.hotel.connectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.GuestDAO;
import by.borisevich.hotel.dao.Utils;
import by.borisevich.hotel.entity.Booking;
import by.borisevich.hotel.entity.Guest;
import jdk.jshell.execution.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuestDaoImpl implements GuestDAO {

    private static final String QUERY = "INSERT INTO Guest (passport, telephoneNumber, firstName, lastName, birthDate, user)"
            + " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String QUERY_GET_GUESTS_BY_IDS = "SELECT id, passport, telephoneNumber, firstName, lastName, birthDate, user FROM guest WHERE id IN (";
    private static final String QUERY_UPDATE_GUEST ="UPDATE Guest SET passport = ?, telephoneNumber = ?, firstName = ?, lastName = ?, birthDate = ?, user = ? WHERE id=?";
    private static final String QUERY_DELETE_GUEST = "DELETE FROM Guest WHERE id=?";
    private static final String QUERY_GET_GUESTS_BY_USER = "SELECT * FROM GUEST WHERE userId=?";
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
            ps.setDate(5, new java.sql.Date(guest.getBirthDate().getTime()));
            ps.setInt(6, guest.getUser());

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
    public List<Guest> getGuestsByIds(List<Integer> guestsIds) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Guest> guests = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(Utils.createQuery(QUERY_GET_GUESTS_BY_IDS, guestsIds.size()));
            int parameterIndex = 1;
            for (Iterator< Integer > iterator = guestsIds.iterator(); iterator.hasNext();) {
                Integer id = (Integer) iterator.next();
                ps.setInt(parameterIndex++, id);
            }
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                guests.add(new Guest(resultSet));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return guests;
    }

    @Override
    public List<Guest> getGuestsByUserId(int userId) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Guest> guestsByUser = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_GUESTS_BY_USER);
            ps.setInt(1, userId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                guestsByUser.add(new Guest(resultSet));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return guestsByUser;
    }


    @Override
    public void update(Guest guest) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_UPDATE_GUEST);
            ps.setString(1, guest.getPassport());
            ps.setString(2, guest.getTelephoneNumber());
            ps.setString(3, guest.getFirstName());
            ps.setString(4, guest.getLastName());
            ps.setDate(5, new java.sql.Date(guest.getBirthDate().getTime()));
            ps.setInt(6, guest.getUser());
            ps.setInt(7, guest.getGuestId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating guest failed, no rows affected.");
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pool.closeConnection(connection, ps);
        }
    }

    @Override
    public void delete(Guest guest) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_DELETE_GUEST);
            ps.setInt(1, guest.getGuestId());
            ps.execute();
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pool.closeConnection(connection, ps);
        }

    }



}
