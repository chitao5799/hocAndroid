package mtha.fithou.myorderfood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class InsFoodActivity extends AppCompatActivity
implements View.OnClickListener {
    static final int PICK_IMAGE=1;
    EditText etID, etName, etPrice, etDes, etAmount;
    ImageView imgFood;
    Bitmap photo;
    Button btnSave, btnClose, btnSelect;
    FoodModel foodModel ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ins_food);
        getViews();
    }

    private void getViews(){
        etID = (EditText)findViewById(R.id.etID);
        etName = (EditText)findViewById(R.id.etName);
        etDes = (EditText)findViewById(R.id.etDes);
        etPrice =(EditText)findViewById(R.id.etPrice);
        etAmount = (EditText)findViewById(R.id.etAmount);
        imgFood = (ImageView) findViewById(R.id.imgFood);
        btnSave = (Button)findViewById(R.id.btnSave);
        btnClose = (Button)findViewById(R.id.btnClose);
        btnSelect = (Button)findViewById(R.id.btnSelectImg);
        //dang ky xu ly su kien cho cac button
        btnSelect.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        //xu ly su kien cho cac nut lenh o day
        switch (view.getId()){
            case R.id.btnSave:
                //ins vao csdl
                foodModel = new FoodModel(getApplicationContext());
                long kq = foodModel.insFood(getFoodInfo());
                if(kq >-1) Toast.makeText(InsFoodActivity.this,"Insert " + kq +
                        " ban ghi thanh cong ", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(InsFoodActivity.this, "Insert that bai",
                            Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnSelectImg:
                //lay anh tu gallery va hien len imageView
                Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);getIntent.setType("image/*");
                Intent pickIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                pickIntent.setType("image/*");
                Intent chooserIntent = Intent.createChooser(getIntent,
                        getString(R.string.food_imge));
                chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[]{pickIntent});
                startActivityForResult(chooserIntent, PICK_IMAGE);
                break;
            case R.id.btnClose:
                finish();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //xu ly du lieu tra ve khi chon xong image
        if (requestCode == PICK_IMAGE &&
                resultCode == Activity.RESULT_OK) {

            try {
                Uri imageUri = data.getData();
                photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(),
                        imageUri);
                //set vao doi tuong imageView
                imgFood.setImageBitmap(photo);
            } catch (IOException e) {

            }

            return;

        }
    }
    private Food getFoodInfo(){
        Food food = new Food();
        food.id = etID.getText().toString();
        food.name = etName.getText().toString();
        food.picture = convertBitmapToArrayByte(imgFood);
        food.amount = etAmount.getText().toString();
        food.price = etPrice.getText().toString();
        food.des = etDes.getText().toString();
        return food;
    }

    public byte[] convertBitmapToArrayByte(ImageView imageView){
        //khoi tao doi tuong cua lop BitmapDrawable
        BitmapDrawable bitmapDrawable =(BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        //thuc hien convert sang byte[]
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
       return stream.toByteArray();
    }
    private boolean checkPermissionReadExternalStorage() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;        } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;        }
        } else {
            return true;    }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                    @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            //xử lý ở đây
        }
    }

}