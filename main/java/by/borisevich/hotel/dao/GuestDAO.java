package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.Guest;

import java.util.List;

public interface GuestDAO {

    List<Guest> getGuestsByIds(int[] guestIds) throws DAOException;

    void addGuest(Guest guest) throws DAOException;

    void update(Guest guest)throws DAOException;

    void delete(Guest guest) throws DAOException;


}
