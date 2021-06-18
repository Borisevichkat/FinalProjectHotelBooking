package by.borisevich.hotel.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Invoice implements Serializable {
    private int invoiceId;
    private double totalCost;
    private String status;
    private int user;
    private int booking;

    public Invoice() {
    }

    public Invoice(int invoiceId, double totalCost, String status, int user, int booking) {
        this.invoiceId = invoiceId;
        this.totalCost = totalCost;
        this.status = status;
        this.user = user;
        this.booking = booking;
    }

    public Invoice(ResultSet resultSet) {
        try {
            this.invoiceId = resultSet.getInt(1);
            this.totalCost = resultSet.getDouble(2);
            this.status = resultSet.getString(3);
            this.booking = resultSet.getInt(4);
        } catch (SQLException e) {
            //
        }
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getBooking() {
        return booking;
    }

    public void setBooking(int booking) {
        this.booking = booking;
    }
}
