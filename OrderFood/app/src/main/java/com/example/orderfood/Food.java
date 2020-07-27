package com.example.orderfood;

public class Food {
    String id,name,mota,gia,soluong;
    byte[] picture;

    public Food() {
    }

    public Food(String id, String name, String mota, String gia, String soluong, byte[] picture)
    {
        this.id = id;
        this.name = name;
        this.mota = mota;
        this.gia = gia;
        this.soluong = soluong;
        this.picture = picture;
    }

}
