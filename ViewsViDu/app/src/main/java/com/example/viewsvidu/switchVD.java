package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class switchVD extends AppCompatActivity {
    Switch aSwitchWifi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_vd);

        aSwitchWifi=(Switch)findViewById(R.id.switchWifi);
        aSwitchWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked)//mặc đinh biến isChecked luôn == true
                {
                    Toast.makeText(switchVD.this,"Đã mở wifi",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(switchVD.this,"Bạn đã tắt wifi",Toast.LENGTH_LONG).show();
            }
        });
    }
}