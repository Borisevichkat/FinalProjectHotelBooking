package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.GuestBookingRoomFund;

import java.util.ArrayList;
import java.util.List;

public interface GuestBookingRoomFundDao {

    ArrayList<GuestBookingRoomFund> getByBookingRoomFundIds(int[] bookingRoomFundIds) throws DAOException;

    void update(List<GuestBookingRoomFund> guestBookingRoomFunds) throws DAOException;

    void remove(List<GuestBookingRoomFund> guestBookingRoomFunds) throws DAOException;
}
