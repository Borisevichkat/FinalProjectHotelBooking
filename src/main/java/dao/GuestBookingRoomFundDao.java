package dao;

import entity.GuestBookingRoomFund;

import java.util.List;

public interface GuestBookingRoomFundDao {

    List<GuestBookingRoomFund> getGuestBookingRoomFundsByBookingRoomFundIds(int[] bookingRoomFundIds);
    void update(List<GuestBookingRoomFund> guestBookingRoomFunds);
    void remove(List<GuestBookingRoomFund> guestBookingRoomFunds);
}
