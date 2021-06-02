package dao;

import entity.Booking;

import java.util.ArrayList;

public interface BookingDAO {

    ArrayList<Booking> findByUserId(int userId);
    ArrayList<Booking> findByIds(int[] bookingIds);
    void createBooking(Booking booking);
    void updateBooking(Booking booking);
}
