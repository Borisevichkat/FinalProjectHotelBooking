package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.RoomFund;

import java.util.ArrayList;
import java.util.List;

public interface RoomFundDAO {

    ArrayList<RoomFund> getAllRooms() throws DAOException;

    ArrayList<RoomFund> findRoomsByIds(List<Integer> roomIds) throws DAOException;

}
