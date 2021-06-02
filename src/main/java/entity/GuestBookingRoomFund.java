package entity;

public class GuestBookingRoomFund {
    private int guestBookingRoomFundId;
    private int guestId;
    private int bookingRoomFundId;

    public GuestBookingRoomFund(){
    }

    public GuestBookingRoomFund(int guestBookingRoomFundId, int guestId, int bookingRoomFundId) {
        this.guestBookingRoomFundId = guestBookingRoomFundId;
        this.guestId = guestId;
        this.bookingRoomFundId = bookingRoomFundId;
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
