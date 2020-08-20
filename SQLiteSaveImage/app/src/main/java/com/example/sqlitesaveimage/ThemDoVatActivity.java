package com.example.sqlitesaveimage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ThemDoVatActivity extends AppCompatActivity {
    EditText edtName,edtMota;
    ImageButton imgBtnCamera,imgBtnFolder;
    ImageView imgAnh;
    Button btnAdd,btnHuy;

    final int request_code_camera=9876,request_code_folder=2345;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_do_vat);

        AnhXa();
        imgBtnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,request_code_camera);

                //với android từ 6. trở lên, khi truy cập camera thì 1 dialog hiện lên xin cấp quyền
//                ActivityCompat.requestPermissions(
//                        ThemDoVatActivity.this,
//                        new String[]{Manifest.permission.CAMERA},
//                        request_code_camera
//                );
            }
        });
        imgBtnFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,request_code_folder);

//                ActivityCompat.requestPermissions(
//                        ThemDoVatActivity.this,
//                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
//                        request_code_folder
//                );
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //chuyển ảnh ở imageview sang byte[]
                BitmapDrawable bitmapDrawable= (BitmapDrawable) imgAnh.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);//100 là quality, số càng nhỏ thì ảnh càng chất lượng
                byte[] hinhAnh=byteArray.toByteArray();

                MainActivity.database.InsertDoVat(
                        edtName.getText().toString().trim(),
                        edtMota.getText().toString().trim(),
                        hinhAnh
                );
                Toast.makeText(ThemDoVatActivity.this,"thêm thành công",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ThemDoVatActivity.this,MainActivity.class));
                finish();
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThemDoVatActivity.this,MainActivity.class));
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        //hàm này để xử lý khi dialog hiện lên xin cấp quyền
        switch (requestCode)
        {
            case request_code_camera:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent,request_code_camera);
                }else{
                    Toast.makeText(ThemDoVatActivity.this,"bạn đã không cho phép mở camera",Toast.LENGTH_SHORT).show();
                }
                break;
            case request_code_folder:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Intent intent=new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,request_code_folder);
                }else{
                    Toast.makeText(ThemDoVatActivity.this,"bạn đã không cho phép mở file",Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==request_code_camera && resultCode==RESULT_OK && data!=null)
        {
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imgAnh.setImageBitmap(bitmap);
        }
        if(requestCode==request_code_folder && resultCode==RESULT_OK && data!=null)
        {
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgAnh.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void AnhXa()
    {
        edtMota=(EditText)findViewById(R.id.edtMota);
        edtName=(EditText)findViewById(R.id.edtName);
        imgBtnCamera=(ImageButton)findViewById(R.id.imgBtnCamera);
        imgBtnFolder=(ImageButton)findViewById(R.id.imgBtnUploadImage);
        imgAnh=(ImageView)findViewById(R.id.imgHinhAnh);
        btnAdd=(Button)findViewById(R.id.buttonAdd);
        btnHuy=(Button)findViewById(R.id.buttonHuy);

    }
}