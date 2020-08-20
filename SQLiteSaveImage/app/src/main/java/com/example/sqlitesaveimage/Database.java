package com.example.sqlitesaveimage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String query)
    {
        SQLiteDatabase database=getWritableDatabase();
        database.execSQL(query);
    }
    public Cursor GetData(String query)
    {
        SQLiteDatabase database=getReadableDatabase();
        return database.rawQuery(query,null);
    }
    public  void InsertDoVat(String ten,String mota,byte[] hinh)
    {
        SQLiteDatabase database=getWritableDatabase();
        String sql="insert into DoVat values(null,? , ?, ?)";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,ten);// 1 là ? đầu tiên trong query insert
        statement.bindString(2,mota);
        statement.bindBlob(3,hinh);

        statement.executeInsert();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
