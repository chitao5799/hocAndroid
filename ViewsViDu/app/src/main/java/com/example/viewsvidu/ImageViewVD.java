package com.example.viewsvidu;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;

public class ImageViewVD extends AppCompatActivity {
    RelativeLayout manHinhApp;
    ImageView imgView;
    Button btnChangBackground;
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view_vd);


        manHinhApp=(RelativeLayout)findViewById(R.id.manHinh);
        imgView=(ImageView)findViewById(R.id.imgView3);

      //  manHinhApp.setBackgroundColor(Color.rgb(245,21,123));
     //   manHinhApp.setBackgroundColor(Color.argb((float) 0.2,0,0,0));
       // manHinhApp.setBackgroundResource(int resid);
        manHinhApp.setBackgroundResource(R.drawable.wallpaper2);//chọn Resource từ thư mục drawable thì chọn tham số kiểu int như dòng trên.
        imgView.setImageResource(R.drawable.icons_user);

        /*//wrok ok
        ArrayList<Integer> arrImage=new ArrayList<>();
        arrImage.add(R.drawable.wallpaper1);
        arrImage.add(R.drawable.wallpaper2);
        arrImage.add(R.drawable.wallpaper3);
        arrImage.add(R.drawable.wallpaper4);
        arrImage.add(R.drawable.wallpaper5);
        arrImage.add(R.drawable.wallpaper6);
        Random random=new Random();
        int position=random.nextInt(arrImage.size())+1;
        manHinhApp.setBackgroundResource(arrImage.get(position));*/

       /* //doesn't work
        Random random=new Random();
        int position=random.nextInt(6)+1;
        int resID = getResources().getIdentifier("org.anddev.android.ViewsViDu:drawable/wallpaper"+position, null, null);
        manHinhApp.setBackgroundResource(resID);*/

       btnChangBackground=(Button)findViewById(R.id.btnChangeBg);

       btnChangBackground.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Random random=new Random();
               int position=random.nextInt(6)+1;
               String nameimg="wallpaper"+position;
               int resID= ImageViewVD.this.getResources().getIdentifier(nameimg, "drawable",ImageViewVD.this.getPackageName());
               manHinhApp.setBackgroundResource(resID);
           }
       });


    }
}