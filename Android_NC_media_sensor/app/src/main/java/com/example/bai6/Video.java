package com.example.bai6;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class Video extends AppCompatActivity {

    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    public void play(android.view.View view)
    {
        videoView = findViewById(R.id.videoFrame);
        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.sample);

        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();

    }

    public void stop(android.view.View view)
    {
        videoView = findViewById(R.id.videoFrame);
        videoView.stopPlayback();

    }
}