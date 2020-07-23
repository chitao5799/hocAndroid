package com.example.implicitintentvd;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView txtbroswer,txtSendMessage,txtCall;
    Button btnCaptureImage;
    ImageView imgAnh;
    int REQUEST_CODE_CAMERA=2255;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtbroswer=(TextView)findViewById(R.id.txtBrowser);
        txtSendMessage=(TextView)findViewById(R.id.txtSendMessage);
        txtCall=(TextView)findViewById(R.id.txtCall);
        btnCaptureImage=(Button)findViewById(R.id.btnCaptureImage);
        imgAnh=(ImageView)findViewById(R.id.imgAnh);

        txtbroswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/"));//sẽ mở trang web trong trình duyệt (app khác app này) của
                                            // thiết bị, nếu thiết bị có nhiều trình duyệt thì sẽ có cửa sổ bật lên để chọn trình duyệt.
                startActivity(intent);
            }
        });
        txtSendMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.putExtra("sms_body","chào bạn ....."); //"sms_body" là mặc định, tên phải đúng như thế này
                intent.setData(Uri.parse("sms:0356807678")); //"sms:<sđt>" là mặc định
                //sẽ mở app nhắn tin trên thiết bị, với nội dung đang trong ô input nhập tin nhắn là "chào bạn ....."
                startActivity(intent);
            }
        });

        txtCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_DIAL);//ACTION_CALL
                intent.setData(Uri.parse("tel:0356807678"));//mở app gọi điện trên thiết bị, nhập sẵn số chỉ cần bấm nút gọi
                startActivity(intent);
            }
        });

        btnCaptureImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent,REQUEST_CODE_CAMERA);
                //từ android 6.0 trở lên mỗi khi dùng cameara thì tạo hộp thoại hiện lên xin cấp quyền
                //các máy đời trước thì cho dùng luôn ko có hộp thoại xin cấp quyền
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.CAMERA},REQUEST_CODE_CAMERA);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CODE_CAMERA && grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
        {
            Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,REQUEST_CODE_CAMERA);
        }
        else Toast.makeText(MainActivity.this,"bạn đã không cho phép dùng camera",Toast.LENGTH_SHORT).show();
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_CAMERA && resultCode==RESULT_OK && data!=null)
        {
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");//"data" là mặc định
            imgAnh.setImageBitmap(bitmap);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}