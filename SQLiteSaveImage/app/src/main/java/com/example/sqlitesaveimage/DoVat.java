package com.example.sqlitesaveimage;

public class DoVat {
    private int id;
    private String ten,moTa;
    private byte[] hinh;

    public DoVat(int id, String ten, String moTa, byte[] hinh) {
        this.id = id;
        this.ten = ten;
        this.moTa = moTa;
        this.hinh = hinh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
