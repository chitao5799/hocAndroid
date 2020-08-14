package com.example.appnhac;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView txtTitle,txtTimeSong,txtTimeTotal;
    SeekBar skSong;
    ImageButton btnPrev,btnPlay,btnStop,btnNext;
    ArrayList<Song> arraySong;
    int position=0;
    MediaPlayer mediaPlayer;
    Animation animRotate;
    ImageView imgDisk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AnhXa();
        AddSongs();

        animRotate= AnimationUtils.loadAnimation(MainActivity.this,R.anim.disk_rotate);
        KhoiTaoMediaPlayer();

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaPlayer.isPlaying())
                {
                    //nếu đang phát -> pause ->đổi hình
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }
                else
                {
                    //nếu đang dừng -> phát -> đổi hình
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                }
                UpdateTimeSong();
                imgDisk.startAnimation(animRotate);
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                KhoiTaoMediaPlayer();//để sau khi stop mà người dùng ấn play thì lại bắt đầu phát lại bài hát đó
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position++;
                if(position>=arraySong.size())
                {
                    position=0;
                }
                if(mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
            }

        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                position--;
                if(position < 0)
                {
                    position=arraySong.size()-1;
                }
                if(mediaPlayer.isPlaying())
                    mediaPlayer.stop();
                KhoiTaoMediaPlayer();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
            }
        });
        skSong.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //sự kiện người dùng kéo seekbar, đang kéo seekbar nó sẽ vào hàm này, tức là vần giữ tay kéo
                //seekbar nó sẽ làm thay đổi progress và nó sẽ chạy hàm này. và chạy mỗi khi progress thay đổi
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //bắt sự kiện người dùng vừa chạm vào seekbar
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //sự kiện người dùng buông tay ra.
                mediaPlayer.seekTo(skSong.getProgress());
            }
        });
    }

    private void KhoiTaoMediaPlayer() {
        mediaPlayer=MediaPlayer.create(MainActivity.this,arraySong.get(position).getFile());
        txtTitle.setText(arraySong.get(position).getTitle());
        setTimeTotal();
        UpdateTimeSong();
    }
    private void setTimeTotal()
    {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
        txtTimeTotal.setText(simpleDateFormat.format(mediaPlayer.getDuration())+"");
        //gán max của seekbarSong = tổng thời gian milisecond của bài hát
        skSong.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTimeSong()
    {
        final Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("mm:ss");
                txtTimeSong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                skSong.setProgress(mediaPlayer.getCurrentPosition());//nhờ cái getCurrentPosition() nên
                                            // khi kéo seekbar mình set lại currentPosition của media này nên
                                            // position của media nó bị thay đổi
                                               // nên tự động chạy mấy câu lệnh trong đây. Cho nên kéo seekbar tới
                                             //  đâu thì txt time song cũng tự động thay đổi
                //kiểm tra thời gian bài hát nếu kết thúc -> next
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        position++;
                        if(position>=arraySong.size())
                        {
                            position=0;
                        }
                        if(mediaPlayer.isPlaying())
                            mediaPlayer.stop();
                        KhoiTaoMediaPlayer();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                    }
                });
                handler.postDelayed(this,500);
            }
        },100);//hàm handler.postDelayed() chỉ chạy 1 lần.
    }
    private void AddSongs() {
        arraySong=new ArrayList<>();
        arraySong.add(new Song("Ác ma đến từ thiên đường",R.raw.ac_ma_den_tu_thien_duong));
        arraySong.add(new Song("Điều anh biết",R.raw.dieu_anh_biet));
        arraySong.add(new Song("All my friend",R.raw.all_my_friends));
        arraySong.add(new Song("Đáp án của bạn",R.raw.dap_an_cua_ban));
        arraySong.add(new Song("DDU DU DDU DU",R.raw.ddu_du_ddu));
        arraySong.add(new Song("không xác định tên",R.raw.eight));
        arraySong.add(new Song("Hyaku renda",R.raw.hyaku_renka));
        arraySong.add(new Song("Khác biệt to lớn",R.raw.khac_biet_to_lon));
        arraySong.add(new Song("Không thể nói",R.raw.khong_the_noi));
        arraySong.add(new Song("Kill this love",R.raw.kill_this_love));
        arraySong.add(new Song("Mang chủng",R.raw.mang_chung));
        arraySong.add(new Song("Một khúc tương tư",R.raw.mot_khuc_tuong_tu));
        arraySong.add(new Song("Bài hát không xác định tiêu đề",R.raw.nhac2));
        arraySong.add(new Song("Quẻ bói",R.raw.que_boi));
        arraySong.add(new Song("Sakura",R.raw.sakura));
        arraySong.add(new Song("Thần thoại tuyệt đẹp",R.raw.than_thoai_tuyet_dep));
    }

    private void AnhXa() {
        txtTitle=(TextView)findViewById(R.id.tvTenBaiHat);
        txtTimeSong=(TextView)findViewById(R.id.tvTimeProgress);
        txtTimeTotal=(TextView)findViewById(R.id.tvTimeTotal);
        skSong=(SeekBar)findViewById(R.id.seekBarProgress);
        btnPrev=(ImageButton)findViewById(R.id.imgBtnPrevious);
        btnPlay=(ImageButton)findViewById(R.id.imgBtnPlay);
        btnStop=(ImageButton)findViewById(R.id.imgBtnStop);
        btnNext=(ImageButton)findViewById(R.id.imgBtnNext);
        imgDisk=(ImageView)findViewById(R.id.imgDisk);
    }
}