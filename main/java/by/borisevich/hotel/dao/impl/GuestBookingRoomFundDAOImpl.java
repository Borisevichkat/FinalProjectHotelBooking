package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.connectionPool.ConnectionPool;
import by.borisevich.hotel.connectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.GuestBookingRoomFundDao;
import by.borisevich.hotel.entity.GuestBookingRoomFund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GuestBookingRoomFundDAOImpl implements GuestBookingRoomFundDao {

    private static final String QUERY = "INSERT INTO GuestBookingRoomFund (bookingRoomFund, guest)"
            + " VALUES (?, ?)";
    private static final String QUERY_GET_BY_BOOKING_ROOM_FUND_IDS = "SELECT id, bookingRoomFund, guest  FROM Booking WHERE bookingRoomFund = ?";
    private static final String QUERY_DELETE_GUEST_BOOKING_ROOM_FUND = "DELETE FROM guestBookingRoomFund WHERE id=?";
    @Override
    public ArrayList<GuestBookingRoomFund> getByBookingRoomFundIds(List<Integer> bookingRoomFundIds) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<GuestBookingRoomFund> guestBookingRoomFunds = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_BY_BOOKING_ROOM_FUND_IDS);

            int parameterIndex = 1;
            for (Iterator< Integer > iterator = bookingRoomFundIds.iterator(); iterator.hasNext();) {
                Integer id = (Integer) iterator.next();
                ps.setInt(parameterIndex++, id);
            }
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                guestBookingRoomFunds.add(new GuestBookingRoomFund(resultSet));
            }

        }  catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return guestBookingRoomFunds;
    }

    @Override
    public void delete (GuestBookingRoomFund guestBookingRoomFund) throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_DELETE_GUEST_BOOKING_ROOM_FUND);
            ps.setInt(1, guestBookingRoomFund.getGuestBookingRoomFundId());
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
