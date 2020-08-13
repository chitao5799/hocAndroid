package com.example.docbaovoirss;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class DetailNews extends AppCompatActivity {
    WebView wvTinTuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_news);

        wvTinTuc=(WebView)findViewById(R.id.webviewNews);

        Intent intent=getIntent();
        String link=intent.getStringExtra("linkTinTuc");
        Toast.makeText(DetailNews.this,link,Toast.LENGTH_LONG).show();

       // wvTinTuc.loadUrl(link);
        wvTinTuc.loadUrl("https://dantri.com.vn/du-lich/lau-dai-trang-xoa-nay-tung-la-trung-tam-spa-noi-tieng-2000-nam-truoc-20200813092204067.htm");

        wvTinTuc.setWebViewClient(new WebViewClient());//để set hiển thị trên ứng dụng này khi click 1 link khác, ko mở sang trình duyệt mặc định của thiết bị
    }
}