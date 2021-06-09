package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.Booking;

import java.util.ArrayList;

public interface BookingDAO {

    ArrayList<Booking> findByUserId(int userId) throws DAOException;

    ArrayList<Booking> findByIds(int[] bookingIds) throws DAOException;

    void createBooking(Booking booking) throws DAOException;

    void updateBooking(Booking booking) throws DAOException;
}
