package com.example.cocaro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class LichSu extends AppCompatActivity {
    ListView lvLichSu;
    Button btnXoa;
    SQLiteDatabase db;
    Cursor cs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su);

        lvLichSu=(ListView)findViewById(R.id.lvLichSu);
        btnXoa=(Button)findViewById(R.id.btnXoa);
        GetData();

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=openOrCreateDatabase("carohistory.db",MODE_PRIVATE,null);
                db.execSQL("delete from tblcaro");
                db.close();
                GetData();
            }
        });

    }
    private void GetData()
    {
        ArrayList<String> arrScore=new ArrayList<>();
        db=openOrCreateDatabase("carohistory.db",MODE_PRIVATE,null);
        cs=db.rawQuery("Select * from tblcaro",null);
        cs.moveToNext();
        boolean isLast=false;
        for(int i=1;i<=cs.getCount();i++)
        {
            arrScore.add(cs.getString(1)+"  -  "+cs.getString(2));
            cs.moveToNext();
        }
        cs.close();
        db.close();
        ArrayAdapter arrayAdapter=new ArrayAdapter(LichSu.this,android.R.layout.simple_list_item_1,arrScore);
        lvLichSu.setAdapter(arrayAdapter);
    }
}