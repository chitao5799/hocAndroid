package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;

public class ThemMon extends AppCompatActivity implements View.OnClickListener {
    EditText edtId,edtName,edtPrice,edtDes,edtAmount;
    ImageView imgFood;
    Button btnSave,btnClose,btnSelectImage,btnChupAnh;
    private static final int PICK_IMAGE = 222;
    private static final int CAMERA_REQUEST = 111;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_mon);

        getViews();
    }

    private void getViews()
    {
        edtId=(EditText)findViewById(R.id.edtIDinAddFood);
        edtName=(EditText)findViewById(R.id.edtNameInAddFood);
        edtDes=(EditText)findViewById(R.id.edtMoTaInAddFood);
        edtPrice=(EditText)findViewById(R.id.edtDonGiaInAddFood);
        edtAmount=(EditText)findViewById(R.id.edtSLinAddFood);
        imgFood=(ImageView)findViewById(R.id.imgInAddFood);
        btnSave=(Button)findViewById(R.id.btnLuuInAddFood);
        btnClose=(Button)findViewById(R.id.btnThoatInAddFood);
        btnSelectImage=(Button)findViewById(R.id.btnAnhInAddFood);
        btnChupAnh=(Button)findViewById(R.id.btnChupAnh);

        btnSelectImage.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }
    private void GetImageFromDevice()
    {
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);getIntent.setType("image/*");
        Intent pickIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickIntent.setType("image/*");
        Intent chooserIntent = Intent.createChooser(getIntent,
                getString(R.string.supervisor_profile_choose_image_title));
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
        startActivityForResult(chooserIntent, PICK_IMAGE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) //hàm lấy kết quả từ hàm GetImageFromDevice() or ChupAnh()
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK)
        {

            try {
                Uri imageUri = data.getData();
                Bitmap photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                        imageUri);
                imgFood.setImageBitmap(photo);
            } catch (IOException e) {

            }

            return;
        }
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgFood.setImageBitmap(photo);
            return;
        }
    }
    private void ChupAnh()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, CAMERA_REQUEST);}
        else {

        }
    }
    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnAnhInAddFood:
                    GetImageFromDevice();
                break;
            case R.id.btnChupAnh:
                ChupAnh();
                break;
            case R.id.btnLuuInAddFood:
                break;
            case R.id.btnThoatInAddFood:
                finish();
                break;
        }
    }
}