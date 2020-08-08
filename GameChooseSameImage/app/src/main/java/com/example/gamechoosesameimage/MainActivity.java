package com.example.gamechoosesameimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    public  static ArrayList<String> arrName;
    ImageView imgGoc,imgNhan;
    int REQUEST_CODE_IMAGE=123;
    String tenHinhGoc;
    TextView tvDiem;
    int diem=0;
    SharedPreferences luuDiem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgGoc=(ImageView)findViewById(R.id.imgHinhGoc);
        imgNhan=(ImageView)findViewById(R.id.imgNhan);
        tvDiem=(TextView)findViewById(R.id.txtDiem);
        luuDiem=getSharedPreferences("DiemSoGame",MODE_PRIVATE);

        diem=luuDiem.getInt("diem",0);
        tvDiem.setText(diem+"");

        String[] mangTen=getResources().getStringArray(R.array.list_name);
        arrName=new ArrayList<>(Arrays.asList(mangTen));

        //xáo trộn các phần tử trong mảng
        Collections.shuffle(arrName);
        tenHinhGoc=arrName.get(5);
        int idHinh=getResources().getIdentifier(tenHinhGoc,"drawable",getPackageName());//lấy id hình ảnh từ tên hình
        imgGoc.setImageResource(idHinh);

        imgNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,Images.class),REQUEST_CODE_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_IMAGE && resultCode==RESULT_OK && data!=null)
        {
            String tenHinhNhan=data.getStringExtra("tenhinhchon");
            int idHinhChon=getResources().getIdentifier(tenHinhNhan,"drawable",getPackageName());
            imgNhan.setImageResource(idHinhChon);

            if(tenHinhNhan.equals(tenHinhGoc))
            {
                Toast.makeText(MainActivity.this,"chính xác",Toast.LENGTH_SHORT).show();
                diem++;
                LuuDiem();
                //chọn chính xác thì 2s sau tự đổi hình
                new CountDownTimer(2000,100){

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        //xáo trộn các phần tử trong mảng
                        Collections.shuffle(arrName);
                        tenHinhGoc=arrName.get(5);
                        int idHinh=getResources().getIdentifier(tenHinhGoc,"drawable",getPackageName());
                        imgGoc.setImageResource(idHinh);
                    }
                }.start();
            }
            else
            {
                Toast.makeText(MainActivity.this,"sai rồi",Toast.LENGTH_SHORT).show();
                diem--;
                LuuDiem();
            }
            tvDiem.setText(diem+"");
        }
        //kiểm tra người dùng chưa chọn ảnh mà bấm nút back khi dialog cọn ảnh xuất hiện
        if(requestCode==REQUEST_CODE_IMAGE && resultCode==RESULT_CANCELED)
        {
            Toast.makeText(MainActivity.this,"bạn đã không chọn ảnh, muốn xem lại à \n bạn" +
                    " bị trừ -2 điểm",Toast.LENGTH_SHORT).show();
            diem-=2;
            LuuDiem();
            tvDiem.setText(diem+"");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reload,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_reload)
        {
            //xáo trộn các phần tử trong mảng
            Collections.shuffle(arrName);
            tenHinhGoc=arrName.get(5);
            int idHinh=getResources().getIdentifier(tenHinhGoc,"drawable",getPackageName());
            imgGoc.setImageResource(idHinh);
        }
        return super.onOptionsItemSelected(item);
    }
    private void LuuDiem()
    {
        SharedPreferences.Editor editor=luuDiem.edit();
        editor.putInt("diem",diem);
        editor.commit();
    }
}