package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.Booking;

import java.util.ArrayList;
import java.util.List;

public interface BookingDAO {

    ArrayList<Booking> findByUserId(int userId) throws DAOException;

    ArrayList<Booking> findByIds(List<Integer> bookingIds) throws DAOException;

    void createBooking(Booking booking) throws DAOException;

    void updateBooking(Booking booking) throws DAOException;
}
