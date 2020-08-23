package com.example.fragmenthandlegraphic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements TruyenSinhVien{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void DataStudent(Student sv) {
        FragmentStudentInfo fragmentStudentInfo= (FragmentStudentInfo) getFragmentManager().findFragmentById(R.id.fragmentInfo);

        Configuration configuration=getResources().getConfiguration();

        //if(fragmentStudentInfo != null && fragmentStudentInfo.isInLayout())  //cách 1
         if(fragmentStudentInfo != null && configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) //cách 2
         {
             //trường hợp màn hình đt xoay ngang, list sv bên trái, info sv bên phải.
             fragmentStudentInfo.SetInfo(sv);
         }
        else
        {
            //trường hợp màn hình đt thẳng đứng, list sv ở 1 activity, info sv ở 1 activity riêng.
            Intent intent=new Intent(MainActivity.this,StudentInfoActivity.class);
            intent.putExtra("ObjectSV",sv);
            startActivity(intent);
        }
    }
}