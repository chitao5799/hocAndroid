package com.example.bai6;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class Sensor_02 extends AppCompatActivity {

    TextView txtInfo;
    SensorManager sm;
    List list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_02);


        sm = (SensorManager)getSystemService(SENSOR_SERVICE);

        txtInfo = (TextView)findViewById(R.id.txtInfo);

        list = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if(list.size()>0){
            sm.registerListener(
                    sensorListen,
                    (Sensor) list.get(0),
                    SensorManager.SENSOR_DELAY_NORMAL);
        }else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();
        }

    }

    SensorEventListener sensorListen = new SensorEventListener(){
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // Ít dùng
        }
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            txtInfo.setText("x: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);
        }
    };

}