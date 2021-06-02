package entity;

import java.util.Date;

public class Booking {
    private int bookingId;
    private String status;
    private Date dateFrom;
    private Date dateTo;
    private Double totalCost;
    private int userId;

    public Booking(){
    }

    public Booking(int bookingId, String status, Date dateFrom, Date dateTo, Double totalCost, int user) {
        this.bookingId = bookingId;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.totalCost = totalCost;
        User = user;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
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

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public int getUser() {
        return User;
    }

    public void setUser(int user) {
        User = user;
    }
}
