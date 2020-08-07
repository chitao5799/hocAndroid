package com.example.orderfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    Context context;
    static final String DB_NAME="db_food.db";
    static final int DB_VERSION=1;
    //    String tb_name="tbl_food";
//    String ID="id";
//    String NAME="name";
//    String MOTA="mota";
//    String URLANH="urlpicture";
//    String DONGIA="donggia";
//    String SOLUONG="soluong";
//
    public DBhelper(@Nullable Context context)
    {
        super(context, DB_NAME,null, 1);
        this.context=context;
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table tbl_food (id integer primary key, "+
                "name text,des text, price float,amount integer,picture blod);";//định nghĩa câu lệnh tạo bảng

        //thực hiện câu lệnh
        sqLiteDatabase.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("drop table if exists btl_food");
            onCreate(sqLiteDatabase);
    }
}
