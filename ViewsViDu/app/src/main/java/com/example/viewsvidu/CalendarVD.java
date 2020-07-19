package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarVD extends AppCompatActivity {
    TextView txtTime;
    //chọn calendar của thư viên java.util
    Calendar calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_vd);

        txtTime=(TextView)findViewById(R.id.txtCalendar);
        calendar=Calendar.getInstance();

        txtTime.setText("");
        txtTime.append(calendar.getTime() +"\n");// getTime() trả lại ngày tháng năm giờ ... hiện tại
        // + "\n" vừa để xuống dòng và vừa đẻ ép kiểu về string
        txtTime.append(calendar.get(Calendar.DATE )+ "\n");//trả về ngày trong tháng
        txtTime.append(calendar.get(Calendar.MONTH) + "\n"); //tháng hiện tại=Calendar.MONTH + 1
        txtTime.append(calendar.get(Calendar.YEAR) + "\n");//trả về năm hiện tại

        SimpleDateFormat dinhDangNgay=new SimpleDateFormat("dd/MM/yyyy");//MM phải là M in hoa, nếu m thường thì là định dạng khác
        txtTime.append(dinhDangNgay.format(calendar.getTime()) + "\n");//được ngày tháng năm hiện tại, tháng cũng là tháng hiện tại luôn.

        txtTime.append(calendar.get(Calendar.HOUR) + "\n"); //dịnh dạng 12 giờ
        txtTime.append(calendar.get(Calendar.HOUR_OF_DAY)+ "\n"); //định dạng 24 giờ

        SimpleDateFormat dinhDangGio=new SimpleDateFormat("hh:mm:ss a"); //ký tự 'a' là định dạng 12 giờ để hiển thị AM hoặc PM
        //ngoài "a" còn nhiều định dạng khác: https://developer.android.com/reference/java/text/SimpleDateFormat
        txtTime.append(dinhDangGio.format(calendar.getTime())+"");

    }
}