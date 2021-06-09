package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.GuestBookingRoomFundDao;
import by.borisevich.hotel.entity.GuestBookingRoomFund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuestBookingRoomFundDAOImpl implements GuestBookingRoomFundDao {

    private static final String QUERY = "INSERT INTO GuestBookingRoomFund (bookingRoomFund, guest)"
            + " VALUES (?, ?)";
    private static final String QUERY_GET_BY_BOOKING_ROOM_FUND_IDS = "SELECT id, bookingRoomFund, guest  FROM Booking WHERE bookingRoomFund = ?";
    // private static final String QUERY_DELETE_GUEST_BOOKING_ROOM_FUND = "DELETE ";
    @Override
    public ArrayList<GuestBookingRoomFund> getByBookingRoomFundIds(int[] bookingRoomFundIds) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<GuestBookingRoomFund> guestBookingRoomFunds = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_BY_BOOKING_ROOM_FUND_IDS);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                guestBookingRoomFunds.add(new GuestBookingRoomFund(resultSet));
            }
            pool.closeConnection(connection, ps);
        }  catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return new ArrayList<GuestBookingRoomFund>();
    }

    @Override
    public void update(List<GuestBookingRoomFund> guestBookingRoomFunds) throws DAOException{

    }

    @Override
    public void remove(List<GuestBookingRoomFund> guestBookingRoomFunds) throws DAOException{

    }
}
