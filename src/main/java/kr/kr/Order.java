package kr.kr;


public class Order {
    private int orderID;
    private String eventName;
    private int eventID;
    private int price;
    private String artist;
    private String date;

    Order(int orderID, String eventName, int eventID, int price, String artist, String date) {
        this.orderID = orderID;
        this.eventName = eventName;
        this.eventID = eventID;
        this.price = price;
        this.artist = artist;
        this.date = date;
    }

    public int getOrderID() {
        return orderID;
    }

    public String getEventName() {
        return eventName;
    }

    public int getEventID() {
        return eventID;
    }

    public int getPrice() {
        return price;
    }

    public String getArtist() {
        return artist;
    }

    public String getDate() {
        return date;
    }
}
