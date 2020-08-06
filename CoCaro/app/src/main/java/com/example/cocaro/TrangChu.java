package com.example.cocaro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import static android.app.PendingIntent.getActivity;

public class TrangChu extends AppCompatActivity {
    Button btnPlay,btnExit;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        /*//chạy câu lệnh tạo bảng này 1 lần rồi comment lại
        db=openOrCreateDatabase("carohistory.db",MODE_PRIVATE,null);
        String sql1="DROP TABLE IF EXISTS tblcaro;";
        String sql= "CREATE TABLE tblcaro(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,time text, winner text)";
        db.execSQL(sql1);
        db.execSQL(sql);
//        String sql3="INSERT INTO tblcaro(time,winner) VALUES ( 'MANOJ KUMAR', 'Meerut,UP,INDIA' )";
//        db.execSQL(sql3);
        db.close();
*/

        btnPlay=(Button)findViewById(R.id.btnPlayGame);
        btnExit=(Button)findViewById(R.id.btnExitGame);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(TrangChu.this,TrangMenu.class);
                startActivity(intent);
            }
        });
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
    }
}