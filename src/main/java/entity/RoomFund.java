package entity;

public class RoomFund {
    private int roomId;
    private String type;
    private double price;
    private String view;
    private int capacity;
    private int roomQuantity;

    public roomFund(){
    }

    public RoomFund(int roomId, String type, double price, String view, int capacity, int roomQuantity) {
        this.roomId = roomId;
        this.type = type;
        this.price = price;
        this.view = view;
        this.capacity = capacity;
        this.roomQuantity = roomQuantity;
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
