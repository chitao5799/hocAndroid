package com.example.cocaro;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridBanCo;
    AdapterGridViewCustom adapterGridView;
    // ArrayList<String > list;
    ArrayList<CustomTextView > list;
    TextView txtCurrentPlayer;
    boolean isXplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addData();
      /*  gridBanCo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
           //     CustomTextView tv= (CustomTextView) gridBanCo.getItemAtPosition(position);//(CustomTextView) adapterView.getItemAtPosition(position);
            //   tv.setText("okokoko");     //don't work
              //  adapterGridView.notifyDataSetChanged();
             //  ((TextView)view).setText("kkkkkkk");   //don't work
             //   CustomTextView tv= (CustomTextView) view.findViewById((int)id);
              //  tv.setText("okokoko");    //don't work
                Toast.makeText(MainActivity.this,"ngoài x if",Toast.LENGTH_SHORT).show();
                if(isXplayer)
                {
                    txtCurrentPlayer.setText("X");
                    Toast.makeText(MainActivity.this,"vào x if",Toast.LENGTH_SHORT).show();
                    isXplayer=false;
                }
                else
                {
                    txtCurrentPlayer.setText("O");
                    isXplayer=true;
                }
            }
        });*/
        gridBanCo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"ngoài x if",Toast.LENGTH_SHORT).show();
                txtCurrentPlayer.setText("ppp");
            }
        });
    }

    private void addData()
    {
        for (int i=0;i<266;i++)
            list.add(new CustomTextView(MainActivity.this));
        adapterGridView.notifyDataSetChanged();
    }

    private void init() {
        txtCurrentPlayer=(TextView)findViewById(R.id.txtCurrentPlayer);
        isXplayer=true;
        gridBanCo=(GridView)findViewById(R.id.gridBanCo);
        list=new ArrayList<>();
        adapterGridView=new AdapterGridViewCustom(this,R.layout.o_caro,list);
        gridBanCo.setAdapter(adapterGridView);
    }
}