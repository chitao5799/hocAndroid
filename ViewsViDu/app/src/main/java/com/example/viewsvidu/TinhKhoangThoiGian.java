package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TinhKhoangThoiGian extends AppCompatActivity {
    TextView txtKetQua;
    Button btnTinh;
    EditText edtNgay1,edtNgay2;
    Calendar calendar1,calendar2;
    SimpleDateFormat dinhDangNgay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tinh_khoang_thoi_gian);

        AnhXa();
       dinhDangNgay=new SimpleDateFormat("dd/MM/yyyy");
       edtNgay1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ChonNgay1();
           }
       });
       edtNgay2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ChonNgay2();
           }
       });
       btnTinh.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               //getTimeInMillis trả về thời gian hiện tại là milisecond
               //vì 2 hàm ChonNgay1,2 đã set lại thời gian hiện tại của 2 calendar lên mới dùng được hàm getTimeInMillis
               //chia cho (1000*60*60*24) vì đổi sang giờ mà 1 giây =1000 miligiây , 1 phút=60 giây , 1 giờ=60 phút,1 ngày =24 giờ
               int khoangCachNgay= (int) ((calendar2.getTimeInMillis()-calendar1.getTimeInMillis())/(1000*60*60*24));
               if(khoangCachNgay<0)
               {
                   Toast.makeText(TinhKhoangThoiGian.this,"chọn ngày thứ hai phải lớn hơn ngày thứ nhất",Toast.LENGTH_SHORT).show();
                    return;
               }
               txtKetQua.setText("khoảng cách giữa 2 ngày là: "+khoangCachNgay + " ngày");
           }
       });
    }

    private void AnhXa()
    {
        txtKetQua=(TextView)findViewById(R.id.textViewKetQua);
        btnTinh=(Button)findViewById(R.id.btnTinh);
        edtNgay1=(EditText)findViewById(R.id.edtNgay1);
        edtNgay2=(EditText)findViewById(R.id.edtNgay2);
    }
    private  void ChonNgay1()
    {
        calendar1=Calendar.getInstance();
        int nam=calendar1.get(Calendar.YEAR);
        int thang=calendar1.get(Calendar.MONTH);
        int ngay=calendar1.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(TinhKhoangThoiGian.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar1.set(i,i1,i2);
                edtNgay1.setText(dinhDangNgay.format(calendar1.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private  void ChonNgay2()
    {
        calendar2=Calendar.getInstance();
        int nam=calendar2.get(Calendar.YEAR);
        int thang=calendar2.get(Calendar.MONTH);
        int ngay=calendar2.get(Calendar.DATE);
        DatePickerDialog datePickerDialog=new DatePickerDialog(TinhKhoangThoiGian.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar2.set(i,i1,i2);
                edtNgay2.setText(dinhDangNgay.format(calendar2.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
}