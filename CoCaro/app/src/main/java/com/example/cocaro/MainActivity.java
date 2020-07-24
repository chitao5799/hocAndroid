package com.example.cocaro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridBanCo;
    AdapterGridViewCustom adapterGridView;
     ArrayList<String > list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addData();
        gridBanCo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
               // TextView tv= (TextView) gridBanCo.getItemAtPosition(position);//(TextView) adapterView.getItemAtPosition(i);
              //  tv.setText("okokoko");

            }
        });
    }

    private void addData()
    {
        for (int i=0;i<308;i++)
            list.add("");
        adapterGridView.notifyDataSetChanged();
    }

    private void init() {
        gridBanCo=(GridView)findViewById(R.id.gridBanCo);
        list=new ArrayList<>();
        adapterGridView=new AdapterGridViewCustom(this,R.layout.o_caro,list);
        gridBanCo.setAdapter(adapterGridView);
    }
}