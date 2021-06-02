package dao;

import entity.RoomFund;

import java.util.ArrayList;

public interface RoomFundDAO {

    ArrayList<RoomFund> getAllRooms();
    ArrayList<RoomFund> findRoomsByIds(int[] roomIds);

}
