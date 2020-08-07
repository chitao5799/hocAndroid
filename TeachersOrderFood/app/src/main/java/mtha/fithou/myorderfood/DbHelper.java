package mtha.fithou.myorderfood;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    Context context;
    static final String DB_NAME ="db_food.db";

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql =" create table tbl_food (id Integer primary key, " +
                "name text, des text, price float, " +
                "amount Integer, picture blod );  ";//dinh nghia cau lenh tao bang o day
        //thuc thi cau lenh
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS tbl_food" );
        //goi lai phuong thuc oncreate de tao lai csdl
        onCreate(sqLiteDatabase);
    }
}
