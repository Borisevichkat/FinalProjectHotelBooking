package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.GuestBookingRoomFund;

import java.util.ArrayList;
import java.util.List;

public interface GuestBookingRoomFundDao {

    ArrayList<GuestBookingRoomFund> getByBookingRoomFundIds(List<Integer> bookingRoomFundIds) throws DAOException;

    void delete(GuestBookingRoomFund guestBookingRoomFunds) throws DAOException;

}
