package com.example.bai6;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class Sensor_01 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_01);

        TextView tv1 = (TextView) findViewById(R.id.txtList);
        tv1.setVerticalScrollBarEnabled(true);
        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mList= mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i = 1; i < mList.size(); i++) {
            tv1.append("\n" + mList.get(i).getName() + "\n" + mList.get(i).getVendor() + "\n" + mList.get(i).getVersion());
            tv1.append("\n-------");
        }

        List<Sensor> mLightSensors = mSensorManager.getSensorList(Sensor.TYPE_LIGHT);

        if (mLightSensors.size()>0)
        {
            // Có cảm biến ánh sáng

        }
        else
        {
            // Không có cảm biến ánh sáng
        }
    }

}