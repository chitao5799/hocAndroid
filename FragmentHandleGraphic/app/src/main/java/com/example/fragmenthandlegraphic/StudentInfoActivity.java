package com.example.fragmenthandlegraphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.text.SimpleDateFormat;

public class StudentInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        Intent intent=getIntent();
        Student sinhvien= (Student) intent.getSerializableExtra("ObjectSV");

        FragmentStudentInfo fragmentStudentInfo= (FragmentStudentInfo) getFragmentManager().findFragmentById(R.id.fragmentInfoSV);
        fragmentStudentInfo.SetInfo(sinhvien);
    }
}