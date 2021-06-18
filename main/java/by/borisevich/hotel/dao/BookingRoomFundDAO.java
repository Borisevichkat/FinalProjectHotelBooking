package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.BookingRoomFund;

import java.util.ArrayList;

public interface BookingRoomFundDAO {

    ArrayList<BookingRoomFund> findByBookingIds(int[] bookingIds) throws DAOException;

    void createBookingRoomFund(BookingRoomFund bookingRoomFund) throws DAOException;

    void update(BookingRoomFund bookingRoomFunds) throws DAOException;
}
