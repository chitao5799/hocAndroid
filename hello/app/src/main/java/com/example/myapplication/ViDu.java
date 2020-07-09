package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class ViDu extends AppCompatActivity {
    ProgressBar pgbar_1;
    ProgressBar pgbar_2;
    Button btnStart,btnDate,btnTime;
    int progress=0;
    TextView tvDate,tvTime;
    AutoCompleteTextView autoTextView;
    final Calendar c=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vi_du);
        getView();
        //tao adapter chua datasoure cho completetextview va itemview
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(ViDu.this,android.R.layout.simple_dropdown_item_1line,countries);
        //set adapter cho autotext
        autoTextView.setAdapter(adapter);
        btnStart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                setProgressbarValue(progress);
            }

        });
    }
    final String[] countries={"viet nam","lao","china","thai lan","singapore","compuchia"};
    private  void getView()
    {
        pgbar_1=(ProgressBar) findViewById(R.id.pgbar1);
        pgbar_2=(ProgressBar) findViewById(R.id.pgbar2);
        btnStart=(Button) findViewById(R.id.btnStart);
        autoTextView=(AutoCompleteTextView) findViewById(R.id.autoText);
        tvDate=(TextView) findViewById(R.id.tvDate);
        tvTime=(TextView) findViewById(R.id.tvTime);
        btnDate=(Button) findViewById(R.id.btnDate);
        btnTime=(Button) findViewById(R.id.btnTime);
        //đăng ký sử lý sự kiện
     //   btnDate.setOnClickListener((View.OnClickListener) this);
     //   btnTime.setOnClickListener((View.OnClickListener) this);
    }
    private  void setProgressbarValue(final  int progress)
    {
        pgbar_2.setProgress(progress);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressbarValue(progress+10);
            }
        }).start();
    }
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnDate:
                int myear =c.get(Calendar.YEAR);
                int mMonth=c.get(Calendar.MONTH);
                int mDay=c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog=new DatePickerDialog(ViDu.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                tvDate.setText(day + '/'+(month+1) +'/'+year);
                            }
                        },myear,mMonth,mDay);
                datePickerDialog.show();
                break;
            case R.id.btnTime:
                int currentHour=c.get(Calendar.HOUR);
                int currentMinute=c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(ViDu.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                tvTime.setText(hour + " : " + minute);
                            }
                        },currentHour,currentMinute, false);
                timePickerDialog.show();
                break;
        }
    }
}