package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

public class CheckBoxVD extends AppCompatActivity {
    CheckBox cbAndroid,cbIOS,cbWindows;
    Button btnChoose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox_vd);

        getViews();
        cbAndroid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if(checked)//checked luôn =true, nghĩa là đã được check ở ô checkbox
                    Toast.makeText(CheckBoxVD.this,"Bạn đã check android",Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(CheckBoxVD.this,"Bạn đã uncheck android",Toast.LENGTH_SHORT).show();
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String monHoc="Bạn đã chọn môn:"+"\n";
                if(cbAndroid.isChecked())
                    monHoc+=cbAndroid.getText()+"\n";
                if(cbIOS.isChecked())
                    monHoc+=cbIOS.getText()+"\n";
                if(cbWindows.isChecked())
                    monHoc+=cbWindows.getText()+"\n";
                Toast.makeText(CheckBoxVD.this,monHoc,Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getViews()
    {
        cbAndroid=(CheckBox)findViewById(R.id.ckBox1);
        cbIOS=(CheckBox)findViewById(R.id.ckBox2);
        cbWindows=(CheckBox)findViewById(R.id.ckBox3);
        btnChoose=(Button)findViewById(R.id.btnChoose);
    }
}