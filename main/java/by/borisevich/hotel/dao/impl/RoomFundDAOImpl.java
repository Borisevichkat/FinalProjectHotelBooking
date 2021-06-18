package by.borisevich.hotel.dao.impl;

import by.borisevich.hotel.connectionPool.ConnectionPool;
import by.borisevich.hotel.connectionPool.ConnectionPoolException;
import by.borisevich.hotel.dao.DAOException;
import by.borisevich.hotel.dao.RoomFundDAO;
import by.borisevich.hotel.entity.RoomFund;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoomFundDAOImpl implements RoomFundDAO {

    private static final String QUERY_GET_ROOM_FUND_BY_ID = "SELECT price, view, capacity, roomsQuantity  FROM RoomFund WHERE Id = ?";
    private static final String QUERY_GET_ROOM_FUND = "SELECT id, price, view, capacity, roomsQuantity, roomType";

    @Override
    public ArrayList<RoomFund> findRoomsByIds(List<Integer> roomIds) throws DAOException {
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<RoomFund> roomFunds= new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_ROOM_FUND_BY_ID);
            int parameterIndex = 1;
            for (Iterator< Integer > iterator = roomIds.iterator(); iterator.hasNext();) {
                Integer id = (Integer) iterator.next();
                ps.setInt(parameterIndex++, id);
            }
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                roomFunds.add(new RoomFund(resultSet));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return roomFunds;
    }

    @Override
    public ArrayList<RoomFund> getAllRooms() throws DAOException{
        Connection connection = null;
        PreparedStatement ps = null;
        ConnectionPool pool = null;
        ArrayList<RoomFund> roomFunds = new ArrayList<>();

        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            ps = connection.prepareStatement(QUERY_GET_ROOM_FUND);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                roomFunds.add(new RoomFund(resultSet));
            }
        } catch (ConnectionPoolException e) {
            throw new DAOException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new DAOException(e);
        } finally {
            pool.closeConnection(connection, ps);
        }
        return roomFunds;
    }
}
