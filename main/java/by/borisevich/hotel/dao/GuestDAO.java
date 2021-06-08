package by.borisevich.hotel.dao;

import by.borisevich.hotel.entity.Guest;

import java.util.List;

public interface GuestDAO {

    List<Guest> getGuestsByIds(int[] guestIds);

    void addGuest(Guest guest);

    void update(Guest guest);

    void delete(Guest guest);


}
