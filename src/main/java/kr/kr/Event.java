package kr.kr;

public class Event {
    private int eventID;
    private String eventName;
    private int price;
    private String date;
    private String artist;

    Event(int eventID, String eventName, int duration, String date, String artist){
        this.eventID = eventID;
        this.eventName = eventName;
        this.price = duration;
        this.date = date;
        this.artist = artist;
    }

    public int getEventID() {
        return this.eventID;
    }

    public String getEventName() {
        return this.eventName;
    }

    public int getPrice() {
        return this.price;
    }

    public String getDate() {
        return this.date;
    }
    public String getArtist(){
        return this.artist;
    }
}
