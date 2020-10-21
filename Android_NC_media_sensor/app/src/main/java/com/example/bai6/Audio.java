package com.example.bai6;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

public class Audio extends AppCompatActivity {

    MediaPlayer mp ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);

        mp = MediaPlayer.create(getApplicationContext(), R.raw.hnkm);



    }

    public void play(android.view.View btnPlay)
    {

        mp.start();

    }

    public void next(android.view.View btnPlay)
    {

        mp.seekTo(mp.getCurrentPosition() + 5000);

    }
}