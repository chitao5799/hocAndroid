package com.example.sqlitesaveimage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnThem;
    ListView lvDoVat;
    ArrayList<DoVat> arrayDoVat;
    DoVatAdapter adapter;
    public static Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database=new Database(this,"quanly.sqlite",null,1);
        String sqlTable="create table if not exists DoVat(Id integer primary key autoincrement, " +
                "Ten varchar(50), MoTa varchar(250), HinhAnh blob)";
        database.QueryData(sqlTable);

        lvDoVat=(ListView)findViewById(R.id.listviewDoVat);
        arrayDoVat=new ArrayList<>();
        adapter=new DoVatAdapter(MainActivity.this,R.layout.dong_do_vat,arrayDoVat);
        lvDoVat.setAdapter(adapter);

        btnThem=(Button)findViewById(R.id.btnAddImage);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ThemDoVatActivity.class));
                finish();
            }
        });


        Cursor cursor=database.GetData("select * from DoVat");
        while (cursor.moveToNext())
        {
            arrayDoVat.add(
                    new DoVat(
                            cursor.getInt(1),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getBlob(3)
                    )
            );
        }
        adapter.notifyDataSetChanged();
    }

}