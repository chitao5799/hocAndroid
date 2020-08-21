package com.example.androidwebservice;

import java.io.Serializable;

public class SinhVien implements Serializable {
    int id,namSinh;
    String hoTen,diaChi;

    public SinhVien(int id, String hoTen, int namSinh, String diaChi) {
        this.id = id;
        this.namSinh = namSinh;
        this.hoTen = hoTen;
        this.diaChi = diaChi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
}
