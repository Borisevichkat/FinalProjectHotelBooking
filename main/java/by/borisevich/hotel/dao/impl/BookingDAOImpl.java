package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.BookingDAO;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.entity.Booking;

import java.sql.*;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {

    private static final String QUERY = "INSERT INTO Booking (status, dateFrom, dateTo, totalCost, userId)"
            + " VALUES (?, ?, ?, ?, ?)";
    private static final String QUERY_GET_BOOKING_BY_USER_ID = "SELECT id, status, dateFrom, dateTo, totalCost  FROM Booking WHERE userId = ?";
    private static final String QUERY_GET_BOOKING_BY_ID = "SELECT status, dateFrom, dateTo, totalCost  FROM Booking WHERE id = ?";
    public BookingDAOImpl() {
    }

    @Override
    public ArrayList<Booking> findByUserId(int userId) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Booking> bookingsByIds = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_BOOKING_BY_USER_ID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                bookingsByIds.add(new Booking(resultSet));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return new ArrayList<Booking>();
    }

    @Override
    public ArrayList<Booking> findByIds(int[] bookingIds) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Booking> bookings = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_BOOKING_BY_ID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                bookings.add(new Booking(resultSet));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return new ArrayList<Booking>();
    }

    @Override
    public void createBooking(Booking booking) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, booking.getStatus());
            ps.setDate(2, (Date) booking.getDateFrom());
            ps.setDate(3, (Date) booking.getDateTo());
            ps.setDouble(4, booking.getTotalCost());
            ps.setInt(5, booking.getUserId());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating booking failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    booking.setBookingId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating booking failed, no ID obtained.");
                }
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
    public void updateBooking(Booking booking) throws DAOException{

    }
}
