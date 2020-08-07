package mtha.fithou.myorderfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class FoodModel {
    DbHelper dbHelper;
    SQLiteDatabase db;
    public FoodModel(Context context){
        dbHelper = new DbHelper(context);
    }

    //them moi food vao csdl
    public long insFood(Food food){
        //mo ket noi csdl de thuc hien ghi
        db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //put du lieu vao doi tuong
        contentValues.put("id", food.id);
        contentValues.put("name",food.name);
        contentValues.put("des", food.des);
        contentValues.put("price", food.price);
        contentValues.put("amount", food.amount);
        contentValues.put("picture", food.picture);
        //thuc hien insert vao csdl
        return db.insert("tbl_food",null,contentValues);
    }

    public ArrayList<Food> getAllFood(){
        db = dbHelper.getReadableDatabase();
        ArrayList<Food> kq = new ArrayList<>();
        Cursor cs = db.rawQuery(" select * from tbl_food ",
                null);
        if(cs.getCount()>0){
            while(cs.moveToNext()){
                Food food = new Food();
                food.id = cs.getString(0);
                food.name = cs.getString(1);
                food.des = cs.getString(2);
                food.price = cs.getFloat(3)+"";
                food.amount = cs.getInt(4)+"";
                food.picture =cs.getBlob(5);
                //add doi tuong food vao list
                kq.add(food);
            }
        }
        return kq;
    }
}
