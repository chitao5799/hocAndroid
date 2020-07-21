package com.example.truyendlimplicitintent;

import java.io.Serializable;

public class HocSinh implements Serializable {
    int tuoi;
    String hoTen;

    public HocSinh(int tuoi, String hoTen) {
        this.tuoi = tuoi;
        this.hoTen = hoTen;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }
}
