package com.example.orderfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FoodModal  {
    DBhelper dBhelper;
    SQLiteDatabase db;
    public FoodModal(Context context)
    {
        dBhelper=new DBhelper(context);
    }
    //insert food vào csdl
    public  long insertFood(Food food)
    {
        db=dBhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        //put du lieu vao doi tượng
        contentValues.put("id",food.id);
        contentValues.put("name",food.name);
        contentValues.put("des",food.mota);
        contentValues.put("price",food.gia);
        contentValues.put("amount",food.soluong);
        contentValues.put("picutrue",food.picture);
        //thực hiện insert vào csdl
        return db.insert("btl_food",null,contentValues);
    }
    public ArrayList<Food> getAllFood()
    {
        db=dBhelper.getReadableDatabase();
        ArrayList<Food> kq=new ArrayList<>();
        Cursor cs=db.rawQuery("select * from tbl_food",null);
        if(cs.getCount()>0)
        {
            while (cs.moveToNext())
            {
                Food food=new Food();
                food.id=cs.getString(0);
                food.name=cs.getString(1);
                food.mota=cs.getString(2);
                food.gia=cs.getFloat(3)+"";
                food.soluong=cs.getInt(4)+"";
                food.picture=cs.getBlob(5);
                //add đối tượng food vào list
                kq.add(food);
            }
        }
        return kq;
    }
}
