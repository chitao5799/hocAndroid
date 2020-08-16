package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.IOException;
import java.net.URL;

public class AudioVideoOnline extends AppCompatActivity {
    Button btnSound,btnVideo;
    ProgressBar pbLoad;
    VideoView vvOnline;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video_online);

        btnSound=(Button)findViewById(R.id.btnOlineSound);
        btnVideo=(Button)findViewById(R.id.btnOnlineVideo);
        vvOnline=(VideoView)findViewById(R.id.videoViewOnline);
        pbLoad=(ProgressBar)findViewById(R.id.progressBarLoading);
        pbLoad.setVisibility(View.GONE);

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri url=Uri.parse("https://khoapham.vn/download/vuoncaovietnam.mp4");
                vvOnline.setVideoURI(url);
                vvOnline.setMediaController(new MediaController(AudioVideoOnline.this));
                vvOnline.start();
            }
        });
        btnSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="https://khoapham.vn/download/vietnamoi.mp3";
                MediaPlayer mediaPlayer=new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepareAsync();
                    //show progressbar
                    pbLoad.setVisibility(View.VISIBLE);
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            pbLoad.setVisibility(View.GONE);
                            mp.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}