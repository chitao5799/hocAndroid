package com.example.animationvd;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgAlpha,imgRotate,imgScale,imgTranslate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgAlpha=(ImageView)findViewById(R.id.imgAlpha);
        imgRotate=(ImageView)findViewById(R.id.imgRotate);
        imgScale=(ImageView)findViewById(R.id.imgScale);
        imgTranslate=(ImageView)findViewById(R.id.imgTranslate);

        final Animation animationAlpha= AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_alpha);
        final Animation animationRotate= AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_rotate);
        final Animation animScale= AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_scale);
        final Animation animTranslate= AnimationUtils.loadAnimation(MainActivity.this,R.anim.anim_stranslate);

        imgTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animTranslate);
            }
        });
        imgScale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animScale);
            }
        });

        imgAlpha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animationAlpha);
            }
        });
        imgRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(animationRotate);
            }
        });
    }
}