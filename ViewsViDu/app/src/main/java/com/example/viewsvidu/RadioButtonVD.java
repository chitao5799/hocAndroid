package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class RadioButtonVD extends AppCompatActivity {
    RadioGroup radioGroupThoiGian;
    RadioButton rbSang,rbTrua,rbChieu;
    Button btnXacNhan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio_button_vd);

        radioGroupThoiGian=(RadioGroup)findViewById(R.id.radioGroupTime);
        rbSang=(RadioButton)findViewById(R.id.rbtnSang);
        rbTrua=(RadioButton)findViewById(R.id.rbtnTrua);
        rbChieu=(RadioButton)findViewById(R.id.rbtnChieu);
        btnXacNhan=(Button)findViewById(R.id.btnXacNhan);

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbSang.isChecked())
                    Toast.makeText(RadioButtonVD.this,rbSang.getText(),Toast.LENGTH_SHORT).show();
                else if(rbTrua.isChecked())
                    Toast.makeText(RadioButtonVD.this,rbTrua.getText(),Toast.LENGTH_SHORT).show();
                else if(rbChieu.isChecked())
                    Toast.makeText(RadioButtonVD.this,rbChieu.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        radioGroupThoiGian.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                switch (id)
                {
                    case R.id.rbtnSang:
                        Toast.makeText(RadioButtonVD.this,"Bạn đã chọn sáng",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbtnTrua:
                        Toast.makeText(RadioButtonVD.this,"Bạn đã chọn trưa",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rbtnChieu:
                        Toast.makeText(RadioButtonVD.this,"Bạn đã chọn chiều",Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }
}