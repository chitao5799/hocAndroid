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
    // ArrayList<String > listTextView;
    //ArrayList<CustomTextView > listTextView;
    ArrayList<clsTextView > listTextView;
    TextView txtCurrentPlayer;
    boolean isXplayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        addData();
        txtCurrentPlayer.setText("X");
        gridBanCo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isXplayer)
                {
                    listTextView.set(i,new clsTextView("X","#ff0000"));
                    isXplayer=!isXplayer;
                    txtCurrentPlayer.setText("O");
                }
                else
                {
                    listTextView.set(i,new clsTextView("O","#00cc00"));
                    isXplayer=!isXplayer;
                    txtCurrentPlayer.setText("X");
                }
               adapterGridView.notifyDataSetChanged();

            }
        });
    }

    private void addData()
    {
        for (int i=0;i<266;i++)
            listTextView.add(new clsTextView("","#ff0000"));
        adapterGridView.notifyDataSetChanged();
    }

    private void init() {
        txtCurrentPlayer=(TextView)findViewById(R.id.txtCurrentPlayer);
        isXplayer=true;
        gridBanCo=(GridView)findViewById(R.id.gridBanCo);
        listTextView=new ArrayList<>();
        adapterGridView=new AdapterGridViewCustom(this,R.layout.o_caro,listTextView);
        gridBanCo.setAdapter(adapterGridView);
    }
}