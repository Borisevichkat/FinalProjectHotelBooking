package entity;

import java.util.Date;

public class BookingRoomFund {
    private int bookingRoomFundId;
    private String status;
    private Date dateFrom;
    private Date dateTo;
    private double price;
    private int booking;
    private int roomFund;

    public BookingRoomFund(){
    }

    public BookingRoomFund(int bookingRoomFundId, String status, Date dateFrom, Date dateTo, double price, int booking, int roomFund) {
        this.bookingRoomFundId = bookingRoomFundId;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.price = price;
        this.booking = booking;
        this.roomFund = roomFund;
    }

    public int getBookingRoomFundId() {
        return bookingRoomFundId;
    }

    public void setBookingRoomFundId(int bookingRoomFundId) {
        this.bookingRoomFundId = bookingRoomFundId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getBooking() {
        return booking;
    }

    public void setBooking(int booking) {
        this.booking = booking;
    }

    public int getRoomFund() {
        return roomFund;
    }

    public void setRoomFund(int roomFund) {
        this.roomFund = roomFund;
    }
}
