package kr.kr;

public class Artist {
    private String name;
    private String phone;
    private int price;
    Artist(String name, String phone, int eventId, int price){
        this.name = name;
        this.phone = phone;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }


    public int getPrice() {
        return price;
    }
}
