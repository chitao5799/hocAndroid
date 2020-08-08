package com.example.loadimgfrominternet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button btnLoad;
    ImageView imgHinh;
    TextView txtStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoad=(Button)findViewById(R.id.btnLoad);
        imgHinh=(ImageView)findViewById(R.id.imgHinh);
        txtStatus=(TextView)findViewById(R.id.txtStatus);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadImageFromInternet().execute("https://icdn.dantri.com.vn/thumb_w/640/2017/1-1510967806416.jpg");
            }
        });
    }
    private class LoadImageFromInternet extends AsyncTask<String,Void, Bitmap>
    {
        Bitmap bitmapHinh=null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtStatus.setText("chuẩn bị bắt đầu load");
        }

        @Override
        protected Bitmap doInBackground(String... strings) //strings này chính là string truyền vào class LoadImageFromInternet
        {
            try {
                URL url=new URL(strings[0]);

                InputStream inputStream=url.openConnection().getInputStream();

                 bitmapHinh= BitmapFactory.decodeStream(inputStream);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmapHinh;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            txtStatus.setText("đã load xong");
            imgHinh.setImageBitmap(bitmap);
        }
    }
}