package com.example.todolist;

public class CongViec {
    int idCV;
    String tenCV;

    public CongViec(int idCV, String tenCV) {
        this.idCV = idCV;
        this.tenCV = tenCV;
    }

    public int getIdCV() {
        return idCV;
    }

    public void setIdCV(int idCV) {
        this.idCV = idCV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }
}
