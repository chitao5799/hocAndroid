package com.example.intentvdkhoapham;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

//thay extends AppCompatActivity thành extends Activity để dùng các function trong life cycle của 1 activity
public class MainActivity extends Activity {
    Button btnMain;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMain=(Button)findViewById(R.id.btnAtMainActivity);
        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,SecondActivity.class);
                startActivity(intent);
            }
        });
        Log.d("function:","onCreate in main acitivity");
        /*
        * để xem được các txt log trong lệnh Log.d() thì vào view -> Tool Windows -> Logcat */
    }
    //chuột phải chọn Generate... -> Overide Method... và chọn các hàm trong life cycle của 1 activity
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("function:","onStart in main acitivity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("function:","onRestart in main acitivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("function:","onResume in main acitivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("function:","onPause in main acitivity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("function:","onStop in main acitivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("function:","onDestroy in main acitivity");
    }
}