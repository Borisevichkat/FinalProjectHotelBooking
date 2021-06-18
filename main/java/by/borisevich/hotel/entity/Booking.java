package by.borisevich.hotel.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class Booking implements Serializable {
    private int bookingId;
    private String status;
    private Date dateFrom;
    private Date dateTo;
    private Double totalCost;
    private int userId;

    public Booking() {
    }

    public Booking(ResultSet resultSet) {
        try {
            this.bookingId = resultSet.getInt(1);
            this.status = resultSet.getString(2);
            this.dateFrom = resultSet.getDate(3);
            this.dateTo = resultSet.getDate(4);
            this.totalCost = resultSet.getDouble(5);

        } catch (SQLException e) {
            //
        }
    }

    public Booking(int bookingId, String status, Date dateFrom, Date dateTo, Double totalCost, int userId) {
        this.bookingId = bookingId;
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.totalCost = totalCost;
        this.userId = userId;
    }

    public Booking(String status, Date dateFrom, Date dateTo, Double totalCost, int userId) {
        this.status = status;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.totalCost = totalCost;
        this.userId = userId;
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

    public int getUserId() {
        return userId;
    }

    public void setUser(int userId) {
        this.userId = userId;
    }
}
