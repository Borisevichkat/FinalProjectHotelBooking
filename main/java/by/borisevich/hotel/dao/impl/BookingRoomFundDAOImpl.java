package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.connectionPool.ConnectionPool;
import by.borisevich.hotel.connectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.BookingRoomFundDAO;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.entity.Booking;
import by.borisevich.hotel.entity.BookingRoomFund;

import java.sql.*;
import java.util.ArrayList;

public class BookingRoomFundDAOImpl implements BookingRoomFundDAO {

    private static final String QUERY = "INSERT INTO BookingRoomFund (status, dateFrom, dateTo, roomFund, booking, price)"
            + " VALUES (?, ?, ?, ?, ?)";
    private static final String QUERY_GET_BY_BOOKING_ID = "SELECT id, status, dateFrom, dateTo, roomFund, price  FROM BookingRoomFund WHERE booking = ?";
    private static final String QUERY_UPDATE_BOOKING_ROOM_FUND = "UPDATE BookingRoomFund SET status = ?, dateFrom = ?, dateTo = ?, roomFund = ?, booking =?, price = ? WHERE id=?";

    @Override
    public ArrayList<BookingRoomFund> findByBookingIds(int[] bookingIds) throws DAOException {

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<BookingRoomFund> bookingRoomFunds = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_BY_BOOKING_ID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                bookingRoomFunds.add(new BookingRoomFund(resultSet));
            }
            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return new ArrayList<BookingRoomFund>();
    }


    @Override
    public void createBookingRoomFund(BookingRoomFund bookingRoomFund) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY, Statement.RETURN_GENERATED_KEYS);
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
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
    }

    @Override
    public void update(BookingRoomFund bookingRoomFund)throws DAOException{

        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_UPDATE_BOOKING_ROOM_FUND);
            ps.setString(1, bookingRoomFund.getStatus());
            ps.setDate(2, new java.sql.Date(bookingRoomFund.getDateFrom().getTime()));
            ps.setDate(3, new java.sql.Date(bookingRoomFund.getDateTo().getTime()));
            ps.setInt(4, bookingRoomFund.getRoomFund());
            ps.setInt(5, bookingRoomFund.getBooking());
            ps.setDouble(6, bookingRoomFund.getPrice());
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating bookingRoomFund failed, no rows affected.");
            }

        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            pool.closeConnection(connection, ps);
        }

    }
}
