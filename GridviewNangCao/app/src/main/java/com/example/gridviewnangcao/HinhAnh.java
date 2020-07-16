package com.example.gridviewnangcao;

public class HinhAnh {
    private String Name;
    private int Hinh;

    public HinhAnh(String name, int hinh) {
        Name = name;
        Hinh = hinh;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getHinh() {
        return Hinh;
    }

    public void setHinh(int hinh) {
        Hinh = hinh;
    }
}
