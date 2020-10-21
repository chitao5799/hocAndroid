package com.example.bai5;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SQLite_01 extends AppCompatActivity {

    SQLiteDatabase db;
    EditText txtID;
    EditText txtMark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_01);

        txtID = findViewById(R.id.txtID);
        txtMark = findViewById(R.id.txtMark);

        db = openOrCreateDatabase("MyDB", MODE_PRIVATE, null);

        Button btnInsert = findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create table
                String sql = "CREATE TABLE IF NOT EXISTS TBLSTUDENTS (ID TEXT, MARK FLOAT)";

                db.execSQL(sql);
                // Insert
                sql = "INSERT INTO TBLSTUDENTS(ID,MARK) VALUES('1',1)";
                db.execSQL(sql);
            }
        });

        Button btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update
                String sql = "UPDATE TBLSTUDENTS SET MARK=? WHERE ID = ?";
                db.execSQL(sql, new String[] {"10","1"});

                //
                DBHandler dbHandler = new DBHandler(SQLite_01.this);
                dbHandler.UpdateUserDetails((float) 5.0,"1");
            }
        });

        Button btnQuery = findViewById(R.id.btnQuery);
        btnQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get from DB
                String MaSV = "1";
                String selectQuery = "SELECT  * FROM TBL_STUDENTS WHERE STUDENT_ID=?";
                Cursor c = db.rawQuery(selectQuery, new String[] {"1"});
                if (c.getCount() > 0)
                {
                    c.moveToFirst();
                    txtMark.setText(c.getString(c.getColumnIndex("MARK")));
                    c.close();
                }

            }
        });

    }
}