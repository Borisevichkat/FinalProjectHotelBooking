package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.GuestBookingRoomFundDao;
import by.borisevich.hotel.entity.GuestBookingRoomFund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestBookingRoomFundDAOImpl implements GuestBookingRoomFundDao {

    private static final String query = "INSERT INTO GuestBookingRoomFund (bookingRoomFund, guest)"
            + " VALUES (?, ?)";
    private static final String queryGetByBookingRoomFundIds = "SELECT id, bookingRoomFund, guest  FROM Booking WHERE bookingRoomFund = ?";

    @Override
    public ArrayList<GuestBookingRoomFund> getByBookingRoomFundIds(int[] bookingRoomFundIds) {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<GuestBookingRoomFund> guestBookingRoomFunds = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(queryGetByBookingRoomFundIds);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                guestBookingRoomFunds.add(new GuestBookingRoomFund(resultSet));
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
        return new ArrayList<GuestBookingRoomFund>();
    }

    @Override
    public void update(List<GuestBookingRoomFund> guestBookingRoomFunds) {

    }

    @Override
    public void remove(List<GuestBookingRoomFund> guestBookingRoomFunds) {

    }
}
