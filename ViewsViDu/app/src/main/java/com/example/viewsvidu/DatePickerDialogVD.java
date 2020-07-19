package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerDialogVD extends AppCompatActivity {
    EditText edtChonNgay;
    Calendar calendar=Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker_dialog_vd);

        edtChonNgay=(EditText)findViewById(R.id.edtChonNgay);
        edtChonNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChonNgay();
            }
        });
    }
    private  void ChonNgay()
    {
        int nam=calendar.get(Calendar.YEAR);
        int thang=calendar.get(Calendar.MONTH);
        int ngay=calendar.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(DatePickerDialogVD.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //i:năm người dùng chọn ở date picker dialog, i1: tháng, i2: ngày
                calendar.set(i,i1,i2); //set lại ngày tháng năm hiện tại của calender để dùng hàm getTime() phía dưới
                SimpleDateFormat dinhDangNgay=new SimpleDateFormat("dd/MM/yyyy");
                edtChonNgay.setText(dinhDangNgay.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}