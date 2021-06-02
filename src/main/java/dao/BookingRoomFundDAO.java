package dao;

import entity.Booking;
import entity.BookingRoomFund;
import entity.RoomFund;

import java.util.ArrayList;

public interface BookingRoomFundDAO {

    ArrayList<BookingRoomFund> findByBookingIds(int[] bookingIds);
    void update(ArrayList<BookingRoomFund> bookingRoomFunds);
}
