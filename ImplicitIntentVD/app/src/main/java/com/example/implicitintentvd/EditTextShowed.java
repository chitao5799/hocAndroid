package com.example.implicitintentvd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditTextShowed extends AppCompatActivity {
    EditText edtName;
    Button btnConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);

        edtName=(EditText) findViewById(R.id.edtText);
        btnConfirm=(Button)findViewById(R.id.btnXacNhan);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=edtName.getText().toString();
                Intent intent=new Intent();
                intent.putExtra("NewName",name);
                setResult(RESULT_OK,intent);
                finish();//để đóng màn hình (activity) hiện tại và quay về màn hình trước đó
            }
        });
    }
}