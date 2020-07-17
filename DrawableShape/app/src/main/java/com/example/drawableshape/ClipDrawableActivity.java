package com.example.drawableshape;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ClipDrawableActivity extends AppCompatActivity {
    ImageView imgView;
    Button btnClip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_drawable);

        imgView=(ImageView)findViewById(R.id.imgView);
        btnClip=(Button)findViewById(R.id.btn);

        imgView.setImageLevel(1000); //set giá trị cho phần nhìn thấy của ảnh
        final ClipDrawable clipDrawable= (ClipDrawable) imgView.getDrawable();
        btnClip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lưu ý Handler là của thư viên android.os
                final Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int currenLevel=clipDrawable.getLevel();
                        //số 10000  là giá trị mà ảnh hiện thị full,để lấy được giá trị này thì
                        // thay số vào imgView.setImageLevel(1000) và xem giá trị nào làm ảnh full
                        if(currenLevel>=10000)
                        {
                            currenLevel=0;
                        }
                        imgView.setImageLevel(currenLevel+1000);
                        handler.postDelayed(this,1000);//gọi lại hàm run này
                        //tức là cứ 1s lại chạy hàm run này, làm nhiệm vụ tương tự CountDownTimer
                    }
                },2000);//đợi hai 2s khi hàm run được gọi thì chạy hàm run này

            }
        });
    }
}