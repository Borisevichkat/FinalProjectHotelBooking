package dao;

import entity.Guest;
import entity.GuestBookingRoomFund;

import java.util.List;

public interface GuestDAO {

    List<Guest> getGuestsByIds(int[] guestIds);
    void createGuest(Guest guest);
    void update(Guest guest);
    void delete(Guest guest);


}
