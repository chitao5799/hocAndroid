package com.example.gridviewnangcao;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gvAnhs;
    ArrayList<HinhAnh> arrImage;
    HinhAnhAdapter anhAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        anhAdapter=new HinhAnhAdapter(this,R.layout.dong_hinh_anh,arrImage);
        gvAnhs.setAdapter(anhAdapter);
        gvAnhs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,arrImage.get(i).getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void AnhXa() {
        gvAnhs=(GridView)findViewById(R.id.gridviewAnhs);
        arrImage=new ArrayList<>();
        arrImage.add(new HinhAnh("ảnh 1",R.drawable.a1));
        arrImage.add(new HinhAnh("ảnh 2",R.drawable.a2));
        arrImage.add(new HinhAnh("ảnh 3",R.drawable.a3));
        arrImage.add(new HinhAnh("ảnh 4",R.drawable.a4));
        arrImage.add(new HinhAnh("ảnh 5",R.drawable.a5));
        arrImage.add(new HinhAnh("ảnh 6",R.drawable.a6));
        arrImage.add(new HinhAnh("ảnh 7",R.drawable.a7));
        arrImage.add(new HinhAnh("ảnh 8",R.drawable.a8));
    }

}