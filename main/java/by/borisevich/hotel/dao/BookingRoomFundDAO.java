package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.BookingRoomFund;

import java.util.ArrayList;

public interface BookingRoomFundDAO {

    ArrayList<BookingRoomFund> findByBookingIds(int[] bookingIds);

    void createBookingRoomFund(BookingRoomFund bookingRoomFund);

    void update(ArrayList<BookingRoomFund> bookingRoomFunds);
}
