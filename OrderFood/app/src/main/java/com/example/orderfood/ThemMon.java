package com.example.orderfood;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ThemMon extends AppCompatActivity implements View.OnClickListener {
    EditText edtId,edtName,edtPrice,edtDes,edtAmount;
    ImageView imgFood;
    Button btnSave,btnClose,btnSelectImage;
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

        btnSelectImage.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnSave.setOnClickListener(this);

    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btnAnhInAddFood:
                break;
            case R.id.btnLuuInAddFood:
                break;
            case R.id.btnThoatInAddFood:
                finish();
                break;
        }
    }
}