package com.example.gamechoosesameimage;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.Collections;

public class Images extends Activity {
    TableLayout myTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_images);

        myTable=(TableLayout)findViewById(R.id.tableImage);
        int soDong=6;
        int soCot=3;

        Collections.shuffle(MainActivity.arrName);
        //tạo dọng và cột
        for(int i=1;i<=soDong;i++)
        {
            TableRow row=new TableRow(this);
            //tạo cột / imageview
            for(int j=1;j<=soCot;j++)
            {
                ImageView img=new ImageView(this);

                Resources r=getResources();
                //chuyển giá trị 100dp sang pixel
                int px= (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,100,r.getDisplayMetrics());

                TableRow.LayoutParams layoutParams=new TableRow.LayoutParams(px,px);
                img.setLayoutParams(layoutParams);//set kích thước cho image

                final int vitri=soCot * (i-1) +j-1;

                int idHinh=getResources().getIdentifier(MainActivity.arrName.get(vitri),"drawable",getPackageName());
                img.setImageResource(idHinh);
                //add imageview vào tablerow
                row.addView(img);

                img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent();
                        intent.putExtra("tenhinhchon",MainActivity.arrName.get(vitri));
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });
            }
            //add tablerow vào tablelayout
            myTable.addView(row);
        }

    }
}