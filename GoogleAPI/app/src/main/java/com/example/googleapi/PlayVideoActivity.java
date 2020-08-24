package com.example.googleapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideoActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    YouTubePlayerView youTubePlayer;
    String idVideo;
    int Request_video=3456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        youTubePlayer=(YouTubePlayerView)findViewById(R.id.playVideoChoosed);

        Intent intent=getIntent();
        idVideo=intent.getStringExtra("idVideoYoutube");
        Toast.makeText(PlayVideoActivity.this,idVideo,Toast.LENGTH_SHORT).show();

        youTubePlayer.initialize(PlaylistYoutubeActivity.API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youtube_Player, boolean b) {
        youtube_Player.loadVideo(idVideo);
        //youtube_Player.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError())
        {
            //lỗi phát video là do người dùng
            youTubeInitializationResult.getErrorDialog(PlayVideoActivity.this,Request_video);
        }
        else{
            Toast.makeText(PlayVideoActivity.this,"error !!!",Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Request_video)
        {
            youTubePlayer.initialize(PlaylistYoutubeActivity.API_KEY,PlayVideoActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}