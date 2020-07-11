package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

public class ProgressBarVD extends AppCompatActivity {
    ProgressBar pgXuLy;
    Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressbar_vd);

        pgXuLy=(ProgressBar)findViewById(R.id.progressBarVD);
        btnStart=(Button)findViewById(R.id.btnStartProgressBar);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //10000 - 10 giây - tổng thời gian thực hiện
                //1000 - 1 giây - thời gian lặp
                CountDownTimer countDownTimer=new CountDownTimer(10000,1000)
                {
                    @Override
                    public void onTick(long l) {
                        int currenProgress = pgXuLy.getProgress();
                        if(currenProgress >= pgXuLy.getMax())
                            currenProgress=0;
                        pgXuLy.setProgress(currenProgress+15);
                    }

                    @Override
                    public void onFinish() {
                        //this.start();//khi chạy song lại gọi lại, sẽ chạy vô hạn
                        Toast.makeText(ProgressBarVD.this,"đã chạy xong countdowntimer.",Toast.LENGTH_SHORT).show();
                    }
                };
                countDownTimer.start();
            }

        });
    }
}