package com.example.listviewnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvTraiCay;
    ArrayList<TraiCay> arrTraiCay;
    TraiCayAdapter traiCayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        traiCayAdapter=new TraiCayAdapter(this,R.layout.dong_trai_cay,arrTraiCay);
        lvTraiCay.setAdapter(traiCayAdapter);
    }

    private void getViews() {
        lvTraiCay=(ListView)findViewById(R.id.lvTraiCay);
        arrTraiCay=new ArrayList<>();

        arrTraiCay.add(new TraiCay("Xoài","xoài mít thái lan",R.drawable.xoai));
        arrTraiCay.add(new TraiCay("Chuối","Chuối tiêu hồng thơm ngon",R.drawable.chuoi));
        arrTraiCay.add(new TraiCay("Mận","mận ngon mùa mới",R.drawable.man));
        arrTraiCay.add(new TraiCay("Táo","Táo mới nhập khẩu",R.drawable.tao));
        arrTraiCay.add(new TraiCay("Chuối","Chuối tây, gì gì đấy",R.drawable.chuoi));
        arrTraiCay.add(new TraiCay("Xoài 2","xoài mít thái lan 2",R.drawable.xoai));
        arrTraiCay.add(new TraiCay("Chuối 2","Chuối tiêu hồng thơm ngon 2",R.drawable.chuoi));
        arrTraiCay.add(new TraiCay("Mận 2","mận ngon mùa mới 2",R.drawable.man));
        arrTraiCay.add(new TraiCay("Táo 2","Táo mới nhập khẩu 2",R.drawable.tao));
        arrTraiCay.add(new TraiCay("Xoài 3","xoài mít thái lan 3",R.drawable.xoai));
        arrTraiCay.add(new TraiCay("Chuối 3","Chuối tiêu hồng thơm ngon 3",R.drawable.chuoi));
        arrTraiCay.add(new TraiCay("Mận 3","mận ngon mùa mới 3",R.drawable.man));
        arrTraiCay.add(new TraiCay("Táo 3","Táo mới nhập khẩu 3",R.drawable.tao));
        arrTraiCay.add(new TraiCay("Xoài","xoài mít thái lan",R.drawable.xoai));
        arrTraiCay.add(new TraiCay("Chuối","Chuối tiêu hồng thơm ngon",R.drawable.chuoi));
        arrTraiCay.add(new TraiCay("Mận","mận ngon mùa mới",R.drawable.man));
        arrTraiCay.add(new TraiCay("Táo","Táo mới nhập khẩu",R.drawable.tao));
        arrTraiCay.add(new TraiCay("Chuối","Chuối tây, gì gì đấy",R.drawable.chuoi));
        arrTraiCay.add(new TraiCay("Xoài 2","xoài mít thái lan 2",R.drawable.xoai));
        arrTraiCay.add(new TraiCay("Chuối 2","Chuối tiêu hồng thơm ngon 2",R.drawable.chuoi));
        arrTraiCay.add(new TraiCay("Mận 2","mận ngon mùa mới 2",R.drawable.man));
        arrTraiCay.add(new TraiCay("Táo 2","Táo mới nhập khẩu 2",R.drawable.tao));
        arrTraiCay.add(new TraiCay("Xoài 3","xoài mít thái lan 3",R.drawable.xoai));
        arrTraiCay.add(new TraiCay("Chuối 3","Chuối tiêu hồng thơm ngon 3",R.drawable.chuoi));
        arrTraiCay.add(new TraiCay("Mận 3","mận ngon mùa mới 3",R.drawable.man));
        arrTraiCay.add(new TraiCay("Táo 3","Táo mới nhập khẩu 3",R.drawable.tao));
    }
}