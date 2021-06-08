package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.RoomFund;

import java.util.ArrayList;

public interface RoomFundDAO {

    ArrayList<RoomFund> getAllRooms();

    ArrayList<RoomFund> findRoomsByIds(int[] roomIds);

}
