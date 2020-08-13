package com.example.volleyvd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/*
* add thư viện volley vào app
* mở file: Gradle Scripts//build.gradle(Module: app)
* thêm  < implementation 'com.android.volley:volley:1.1.1' > vào  { } của dependencies rồi ấn Sync Now ở góc trên bên phải.
* (xem thêm: https://developer.android.com/training/volley)
 *  */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        String url="https://kenh14.vn/";

        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"lỗi",Toast.LENGTH_LONG).show();
                        Log.d("mygeterror",error.toString());
                    }
                }
        );
        requestQueue.add(stringRequest);
    }
}