package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class GridViewCoBan extends AppCompatActivity {
    GridView gvChuCai;
    String[] arrChar={"A","S","D","F","G","H","J","K","L","Z","X","C","V","B",
            "N","M","Q","W","E","R","T","Y","U","I","O","P"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gridview_coban);

        gvChuCai=(GridView)findViewById(R.id.gridviewTen);
        ArrayAdapter adapter=new ArrayAdapter(GridViewCoBan.this,android.R.layout.simple_list_item_1,arrChar);
        gvChuCai.setAdapter(adapter);

        gvChuCai.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(GridViewCoBan.this,""+i,Toast.LENGTH_SHORT).show();
            }
        });
    }
}