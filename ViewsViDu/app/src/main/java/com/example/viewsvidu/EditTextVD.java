package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditTextVD extends AppCompatActivity {
    EditText edtTxt;
    Button btnClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_textvd);

        edtTxt=(EditText) findViewById(R.id.txtName);
        btnClick=(Button) findViewById(R.id.btnClick);
       // edtTxt.setText("gán nội dung");
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noiDung=edtTxt.getText().toString();
                //Toast để hiện 1 string (có thể gồm cả icon nhập từ bàn phím đt) lên màn hình 1 thời gian ngắn tầm vài giây.
                Toast.makeText(EditTextVD.this,noiDung,Toast.LENGTH_LONG).show();
            }
        });
    }
}