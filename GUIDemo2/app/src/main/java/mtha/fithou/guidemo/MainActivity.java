package mtha.fithou.guidemo;

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
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //khai bao doi tuong progressBar
    ProgressBar pgBar, pgBar2;
    AutoCompleteTextView auText;
    Button btnStart, btnTime, btnDate;
    TextView tvDate, tvTime;
    final Calendar c = Calendar.getInstance();
    int progress=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getViews();

        //tao apdapter chua data source va itemview
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_dropdown_item_1line,countries);
        //set adapter cho auto Text
        auText.setAdapter(adapter);
        //inline
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //xu ly su kien click chuot o day
                setProgressValue(progress);
            }
        });

    }
//data source
    final String[] countries = {"Viet Nam", "Lao", "Campuchia", "China", "Singapore"};
    private void getViews(){
        pgBar = (ProgressBar) findViewById(R.id.pgBar);
        pgBar2 = (ProgressBar) findViewById(R.id.pgBar2);
        btnStart = (Button) findViewById(R.id.btnStart);
        auText = (AutoCompleteTextView) findViewById(R.id.auText);
        tvDate = (TextView) findViewById(R.id.tvDate);
        tvTime = (TextView) findViewById(R.id.tvTime);
        btnTime = (Button) findViewById(R.id.btnTime);
        btnDate = (Button) findViewById(R.id.btnDate);
        //dang ky xu ly su kien
        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
    }

    private void setProgressValue(final int progress){
        //thiet lap gia tri ban dau cho progressbar
        pgBar2.setProgress(progress);
        //tao 1 luong de cap nhat lai gia tri cua progress
        new Thread(new Runnable() {
            @Override
            public void run() {
                //thuc hien cap nhat gia tri o day
                try {
                    Thread.sleep(1000);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setProgressValue(progress+1);
            }
        }).start();
    }

   // @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btnDate:
                //viet xu ly su kien o day
                int myear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog =new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                //thiet lap gia tri ngay duoc vao cho tvDate
                                tvDate.setText(day +"/" +(month+1) +"/" + year);
                            }
                        }, myear,mMonth,mDay);
                datePickerDialog.show();
                break;
            case R.id.btnTime:
                int currH = c.get(Calendar.HOUR);
                int currM = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                        tvTime.setText(hour +" : " + minute);

                    }
                },currH,currM,false);
                timePickerDialog.show();
                break;
        }
    }
}
