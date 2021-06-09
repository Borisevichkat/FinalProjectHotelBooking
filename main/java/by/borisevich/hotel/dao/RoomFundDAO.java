package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.RoomFund;

import java.util.ArrayList;

public interface RoomFundDAO {

    ArrayList<RoomFund> getAllRooms() throws DAOException;

    ArrayList<RoomFund> findRoomsByIds(int[] roomIds) throws DAOException;

}
