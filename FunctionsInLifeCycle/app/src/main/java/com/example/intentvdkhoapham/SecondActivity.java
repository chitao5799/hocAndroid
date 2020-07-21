package com.example.intentvdkhoapham;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {
    Button btnSecond;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btnSecond=(Button)findViewById(R.id.btnAtSecondAcitivy);
        btnSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SecondActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        Log.d("function:","onCreate in second acitivity");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("function:","onStart in second acitivity");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("function:","onRestart in second acitivity");

    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("function:","onStop in second acitivity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("function:","onDestroy in second acitivity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("function:","onPause in second acitivity");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("function:","onResume in second acitivity");
    }
}