package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InsertFood extends AppCompatActivity implements View.OnClickListener {
    EditText edtId,edtName,edtPrice,edtDes,edtAmount;
    ImageView imgFood;
    Button btnSave,btnClose,btnSelectImage,btnChupAnh;
    private static final int PICK_IMAGE = 222;
    private static final int CAMERA_REQUEST = 111;
    FoodModel foodModal;
    SQLiteDatabase db;
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
    private Food getFoodInfo()
    {
        Food food=new Food();
        food.id=edtId.getText().toString();
        food.name=edtName.getText().toString();
        food.picture=ConvertBitmapToArray(imgFood);
        food.soluong=edtAmount.getText().toString();
        food.gia=edtPrice.getText().toString();
        food.mota=edtDes.getText().toString();
        return food;
    }
    public  byte[] ConvertBitmapToArray(ImageView imageView)
    {
        BitmapDrawable bitmapDrawable= (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap=bitmapDrawable.getBitmap();
        //thực hiện convert sang mảng byte[]
        ByteArrayOutputStream stream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        return stream.toByteArray();
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
                foodModal=new FoodModel(getApplicationContext());
                long kq=foodModal.insertFood(getFoodInfo());
                if(kq > -1) Toast.makeText(InsertFood.this,"insert "+kq+" ban ghi thành công",Toast.LENGTH_LONG).show();
                else Toast.makeText(InsertFood.this,"insert thất bại",Toast.LENGTH_LONG).show();
                break;
            case R.id.btnThoatInAddFood:
                finish();
                break;
        }
    }
}