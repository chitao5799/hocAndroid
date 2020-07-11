package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.SeekBar;

public class SeekBarVD extends AppCompatActivity {
    SeekBar seekBar2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar_vd);

        seekBar2=(SeekBar)findViewById(R.id.seekBar2);
       // seekBar2.getProgress();//lấy giá trị hiện tại của seekbar -cũng giống như progressbar
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //i là giá trị hiện tại của seekbar
                Log.d("seekbar: ","current value: "+i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //sự kiện khi click chạm vào seekbar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //sự kiện khi buông tay kho chạm seekar

            }
        });
    }
}