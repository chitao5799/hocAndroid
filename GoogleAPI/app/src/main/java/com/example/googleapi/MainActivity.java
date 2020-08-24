package com.example.googleapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;


// https://console.developers.google.com/
//api youtube android:  https://developers.google.com/youtube/android/player/downloads
public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{
    String API_key="AIzaSyBpQhKxdvIFxmyWCenbddrzuFvcSdOilAc"; //key api youtube
    YouTubePlayerView youTubePlayerView;
    int Request_video=3456;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        youTubePlayerView=(YouTubePlayerView)findViewById(R.id.playVideoYoutube);
        youTubePlayerView.initialize(API_key,this);

    }
    /* cần có goole service - play store trong genymotion mới chạy được google api. hoặc chạy bằng AVD của android studio có chplay
    https://inthecheesefactory.com/blog/how-to-install-google-services-on-genymotion/en
    https://androidfilehost.com/?fid=23252070760974384
    * */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.cueVideo("YFynTboV_34");//https://www.youtube.com/watch?v=YFynTboV_34
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        if(youTubeInitializationResult.isUserRecoverableError())
        {
            //lỗi phát video là do người dùng
            youTubeInitializationResult.getErrorDialog(MainActivity.this,Request_video);
        }
        else{
            Toast.makeText(MainActivity.this,"error !!!",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==Request_video)
        {
            youTubePlayerView.initialize(API_key,MainActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}