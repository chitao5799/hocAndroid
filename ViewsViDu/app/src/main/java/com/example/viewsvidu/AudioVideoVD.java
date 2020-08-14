package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class AudioVideoVD extends AppCompatActivity {
    Button btnNhac,btnVideo;
    VideoView playvideo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_video_vd);

        btnNhac=(Button)findViewById(R.id.btnPlayAudio);
        btnVideo=(Button)findViewById(R.id.btnPlayVideo);
        playvideo=(VideoView)findViewById(R.id.videoViewNhac);

        btnNhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer mediaPlayer=MediaPlayer.create(AudioVideoVD.this,R.raw.audio);
                mediaPlayer.start();
            }
        });
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playvideo.setVideoURI(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.mv));
                playvideo.start();

                //MediaController của android.widget
                MediaController mediaController=new MediaController(AudioVideoVD.this);
                mediaController.setMediaPlayer(playvideo);
                playvideo.setMediaController(mediaController);
            }
        });
    }
}