package com.example.json_object;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new ReadJson().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
        /*
        {
        "monhoc" : "Lập trình Android" ,
        "noihoc" : "Trung Tâm Đào Tạo Tin Học Khoa Phạm",
        "website": "http://khoapham.vn",
        "fanpage": "http://facebook.com/khoapham.vn",
        "logo"   : "http://khoapham.vn/public/images/logo-370.png"
        }
        */
    }
    private class ReadJson extends AsyncTask<String,Void,String>
    {
        StringBuilder content=new StringBuilder();
        @Override
        protected String doInBackground(String... strings)
        {
            try {
                URL url=new URL(strings[0]);
                InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject=new JSONObject(s);
                String monhoc=jsonObject.getString("monhoc");
                Toast.makeText(MainActivity.this,monhoc,Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

         //   Toast.makeText(MainActivity.this,"chuỗi json:"+s,Toast.LENGTH_LONG).show();
        }
    }
}