package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.ConnectionPool.ConnectionPool;
import by.borisevich.hotel.ConnectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.RoomFundDAO;
import by.borisevich.hotel.entity.RoomFund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomFundDAOImpl implements RoomFundDAO {

    private static final String QUERY_GET_ROOM_FUND_BY_ID = "SELECT price, view, capacity, roomsQuantity  FROM RoomFund WHERE Id = ?";

    @Override
    public ArrayList<RoomFund> findRoomsByIds(int[] roomIds) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<RoomFund> roomFunds= new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_ROOM_FUND_BY_ID);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                roomFunds.add(new RoomFund(resultSet));
            }
            pool.closeConnection(connection, ps);
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return new ArrayList<RoomFund>();
    }

    @Override
    public ArrayList<RoomFund> getAllRooms() throws DAOException{
        return null;
    }
}
