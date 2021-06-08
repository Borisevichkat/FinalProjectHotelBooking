package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.BookingDAO;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.entity.Booking;

import java.sql.*;
import java.util.ArrayList;

public class BookingDAOImpl implements BookingDAO {

    private static final String query = "INSERT INTO Booking (status, dateFrom, dateTo, totalCost, userId)"
            + " VALUES (?, ?, ?, ?, ?)";
    private static final String queryGetBookingByUserId = "SELECT id, status, dateFrom, dateTo, totalCost  FROM Booking WHERE userId = ?";
    private static final String queryGetBookingById = "SELECT status, dateFrom, dateTo, totalCost  FROM Booking WHERE id = ?";
    public BookingDAOImpl() {
    }

    @Override
    public ArrayList<Booking> findByUserId(int userId) {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Booking> bookingsByIds = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(queryGetBookingByUserId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                bookingsByIds.add(new Booking(resultSet));
            }
            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            //
        } catch (SQLException e) {
            //
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
        return new ArrayList<Booking>();
    }


    @Override
    public ArrayList<Booking> findByIds(int[] bookingIds) {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<Booking> bookings = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(queryGetBookingById);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                bookings.add(new Booking(resultSet));
            }
            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            //
        } catch (SQLException e) {
            //
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
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
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
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pool.closeConnection(connection, ps);
        }
    }

    @Override
    public void updateBooking(Booking booking) {

    }
}
