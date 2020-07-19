package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimePickerDialogVD extends AppCompatActivity {
    TextView txtChonGio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker_dialog_vd);

        txtChonGio=(TextView)findViewById(R.id.textViewChonGio);
        txtChonGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonGio();
            }
        });
    }
    private void ChonGio()
    {
        final Calendar calendar=Calendar.getInstance();
        int gio=calendar.get(Calendar.HOUR_OF_DAY);//trả về giời định dạng 24 giờ
        int phut=calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog=new TimePickerDialog(TimePickerDialogVD.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                SimpleDateFormat dinhDangGio=new SimpleDateFormat("HH:mm:ss");//HH viết hoa là định dạng 24 giờ
                calendar.set(0,0,0,i,i1);
                txtChonGio.setText(dinhDangGio.format(calendar.getTime()));
            }
        },gio,phut,true);
        timePickerDialog.show();
    }
}