package by.borisevich.hotel.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuestBookingRoomFund implements Serializable {
    private int guestBookingRoomFundId;
    private int guestId;
    private int bookingRoomFundId;

    public GuestBookingRoomFund() {
    }

    public GuestBookingRoomFund(int guestBookingRoomFundId, int guestId, int bookingRoomFundId) {
        this.guestBookingRoomFundId = guestBookingRoomFundId;
        this.guestId = guestId;
        this.bookingRoomFundId = bookingRoomFundId;
    }

    public GuestBookingRoomFund(ResultSet resultSet){
        try {
            this.guestBookingRoomFundId = resultSet.getInt(1);
            this.guestId = resultSet.getInt(2);
            this.bookingRoomFundId = resultSet.getInt(3);
        } catch (SQLException e) {
            //
        }
    }

    public int getGuestBookingRoomFundId() {
        return guestBookingRoomFundId;
    }

    public void setGuestBookingRoomFundId(int guestBookingRoomFundId) {
        this.guestBookingRoomFundId = guestBookingRoomFundId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public int getBookingRoomFundId() {
        return bookingRoomFundId;
    }

    public void setBookingRoomFundId(int bookingRoomFundId) {
        this.bookingRoomFundId = bookingRoomFundId;
    }
}
