package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.BookingRoomFundDAO;
import by.borisevich.hotel.entity.BookingRoomFund;

import java.sql.*;
import java.util.ArrayList;

public class BookingRoomFundDAOImpl implements BookingRoomFundDAO {

    private static final String query = "INSERT INTO BookingRoomFund (status, dateFrom, dateTo, roomFund, booking, price)"
            + " VALUES (?, ?, ?, ?, ?)";
    private static final String queryGetByBookingId = "SELECT id, status, dateFrom, dateTo, roomFund, price  FROM BookingRoomFund WHERE booking = ?";


    @Override
    public ArrayList<BookingRoomFund> findByBookingIds(int[] bookingIds) {

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<BookingRoomFund> bookingRoomFunds = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(queryGetByBookingId);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                bookingRoomFunds.add(new BookingRoomFund(resultSet));
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
        return new ArrayList<BookingRoomFund>();
    }


    @Override
    public void createBookingRoomFund(BookingRoomFund bookingRoomFund) {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, bookingRoomFund.getStatus());
            ps.setDate(2, (Date) bookingRoomFund.getDateFrom());
            ps.setDate(3, (Date) bookingRoomFund.getDateTo());
            ps.setInt(4, bookingRoomFund.getBookingRoomFundId());
            ps.setInt(5, bookingRoomFund.getBooking());
            ps.setDouble('6', bookingRoomFund.getPrice());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating bookingRoomFund failed, no rows affected.");
            }

            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    bookingRoomFund.setBookingRoomFundId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating bookingRoomFund failed, no ID obtained.");
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
    public void update(ArrayList<BookingRoomFund> bookingRoomFunds) {

    }
}
