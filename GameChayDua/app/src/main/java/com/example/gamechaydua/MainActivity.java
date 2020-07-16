package com.example.gamechaydua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView btnPlay;
    TextView txtScores;
    CheckBox ckOne,ckTwo,ckThree;
    SeekBar skOne,skTwo,skThree;
    int diem=100;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getViews();
        txtScores.setText(diem+"");
        skThree.setEnabled(false);
        skTwo.setEnabled(false);
        skOne.setEnabled(false);

        final CountDownTimer countDownTimer=new CountDownTimer(60000,300)
        {
            @Override
            public void onTick(long l)
            {
                int number=5;
                Random random=new Random();
                int one=random.nextInt(number);
                int two=random.nextInt(number);
                int three=random.nextInt(number);
                //set win
                if(skOne.getProgress() >= skOne.getMax())
                {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"One Win",Toast.LENGTH_LONG).show();
                    //kiểm tra đặt cược
                    if(ckOne.isChecked())
                    {
                        diem+=10;
                        Toast.makeText(MainActivity.this,"Bạn đoán đúng rồi",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        diem-=10;
                        Toast.makeText(MainActivity.this,"Bạn đoán sai rồi",Toast.LENGTH_LONG).show();
                    }

                    txtScores.setText(diem+"");
                    EnableCheckbox();

                }
                if(skTwo.getProgress() >= skTwo.getMax())
                {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Two Win",Toast.LENGTH_LONG).show();
                    //kiểm tra đặt cược
                    if(ckTwo.isChecked())
                    {
                        diem+=10;
                        Toast.makeText(MainActivity.this,"Bạn đoán đúng rồi",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        diem-=10;
                        Toast.makeText(MainActivity.this,"Bạn đoán sai rồi",Toast.LENGTH_LONG).show();
                    }
                    txtScores.setText(diem+"");
                    EnableCheckbox();
                }
                if(skThree.getProgress() >= skThree.getMax())
                {
                    this.cancel();
                    btnPlay.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this,"Three Win",Toast.LENGTH_LONG).show();
                    //kiểm tra đặt cược
                    if(ckThree.isChecked())
                    {
                        diem+=10;
                        Toast.makeText(MainActivity.this,"Bạn đoán đúng rồi",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        diem-=10;
                        Toast.makeText(MainActivity.this,"Bạn đoán sai rồi",Toast.LENGTH_LONG).show();
                    }
                    txtScores.setText(diem+"");
                    EnableCheckbox();
                }

                skOne.setProgress(skOne.getProgress()+one);
                skTwo.setProgress(skTwo.getProgress()+two);
                skThree.setProgress(skThree.getProgress()+three);

            }

            @Override
            public void onFinish()
            {

            }
        };
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ckOne.isChecked() || ckTwo.isChecked() || ckThree.isChecked())
                {
                    skOne.setProgress(0);
                    skThree.setProgress(0);
                    skTwo.setProgress(0);
                    btnPlay.setVisibility(View.INVISIBLE);
                    countDownTimer.start();
                    DisableCheckbox();
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Bạn phải chọn nhân vật",Toast.LENGTH_LONG).show();
                }
            }
        });
        /** //this.cancel() của CountDownTimer chỉ hoạt động trên API 5.0 trở lên, các android dưới có thể dùng cách sau
         *  để cho cái CountDownTimer thực hiện 1 lần thôi, xét xem nếu mấy con kia chạy tới đích chưa.
         *  Nếu chưa, thì start CountDownTimer lại 1 lần nữa
         CountDownTimer countDownTimer = new CountDownTimer(500,500) {//thực hiện tác vụ 1 lần trong vòng nửa giây
         @Override
         public void onTick(long l) {
         sbOne.setProgress(sbOne.getProgress() + random.nextInt(30));
         sbTwo.setProgress(sbTwo.getProgress() + random.nextInt(30));
         sbThree.setProgress(sbThree.getProgress() + random.nextInt(30));

         }

         @Override
         public void onFinish() {

         int current1=sbOne.getProgress();
         int current2=sbTwo.getProgress();
         int current3=sbThree.getProgress();
         int result=0;
         if(current1< 200&&current2<200&&current3<200){
         this.start(); //Chưa hoàn thành -> thực hiện lại
         }else if (current1>=200){
         Toast.makeText(MainActivity.this, "No.1 Wins!", Toast.LENGTH_SHORT).show();
         }else if(current2>=200){
         Toast.makeText(MainActivity.this, "No.2 Wins!", Toast.LENGTH_SHORT).show();
         }else{
         Toast.makeText(MainActivity.this, "No.3 Wins!", Toast.LENGTH_SHORT).show();
         }
         }
         }
         }.start();
         * */
//        ckOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//            }
//        });
        ckOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bỏ check 2,3
                ckOne.setChecked(true);
                ckThree.setChecked(false);
                ckTwo.setChecked(false);
            }
        });
        ckTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bỏ check 1,3
                ckTwo.setChecked(ckTwo.isChecked());
                ckOne.setChecked(false);
                ckThree.setChecked(false);
            }
        });
        ckThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //bỏ ckeck 1,2
                ckOne.setChecked(false);
                ckTwo.setChecked(false);
                ckThree.setChecked(true);
            }
        });
    }
    private void EnableCheckbox()
    {
//        ckOne.setEnabled(true);
//        ckTwo.setEnabled(true);
//        ckThree.setEnabled(true);
        ckOne.setClickable(true);
        ckTwo.setClickable(true);
        ckThree.setClickable(true);
    }
    private void DisableCheckbox()
    {
//        ckOne.setEnabled(false);
//        ckTwo.setEnabled(false);
       // ckThree.setEnabled(false);
        ckOne.setClickable(false);
        ckTwo.setClickable(false);
        ckThree.setClickable(false);
    }
    private void getViews()
    {
        btnPlay=(ImageView)findViewById(R.id.btnPlay);
        txtScores=(TextView)findViewById(R.id.txtScore);
        ckOne=(CheckBox) findViewById(R.id.ckOne);
        ckTwo=(CheckBox)findViewById(R.id.ckTwo);
        ckThree=(CheckBox)findViewById(R.id.ckThree);
        skOne=(SeekBar)findViewById(R.id.seekbarOne);
        skTwo=(SeekBar)findViewById(R.id.seekbarTwo);
        skThree=(SeekBar)findViewById(R.id.seekbarThree);
    }
}