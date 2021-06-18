package by.borisevich.hotel.entity;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomFund implements Serializable {
    private int roomId;
    private String type;
    private double price;
    private String view;
    private int capacity;
    private int roomQuantity;

    public RoomFund() {
    }

    public RoomFund(int roomId, String type, double price, String view, int capacity, int roomQuantity) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.view = view;
        this.capacity = capacity;
        this.roomQuantity = roomQuantity;
    }

    public RoomFund(ResultSet resultSet){
            try {
                this.roomId = resultSet.getInt(1);
                this.price = resultSet.getDouble(2);
                this.view = resultSet.getString(3);
                this.capacity = resultSet.getInt(4);
                this.roomQuantity = resultSet.getInt(5);
            } catch (SQLException e) {
                //
            }
        }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomQuantity() {
        return roomQuantity;
    }

    public void setRoomQuantity(int roomQuantity) {
        this.roomQuantity = roomQuantity;
    }
}
