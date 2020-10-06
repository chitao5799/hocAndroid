package com.example.kt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

public class LoginActivity extends AppCompatActivity {
    EditText edtUserName,edtPassword;
    Button btnDangNhap,btnDangKy,btnQuenMatKhau,btnDoiMk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        GetViews();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtUserName.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();
                if(!password.equals("") && username.equals("admin"))
                {
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                }
                else
                    Toast.makeText(LoginActivity.this,"Thông tin đăng nhập không đúng",Toast.LENGTH_SHORT).show();
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url="http://miai.vn/sample/reg.php";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
            }
        });
        btnQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,QuenMatKhauActivity.class));
            }
        });
        btnDoiMk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,DoiMatKhauActivity.class));
            }
        });
    }

    private void GetViews() {
        edtUserName=(EditText)findViewById(R.id.edtUsername);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
        btnDangNhap=(Button)findViewById(R.id.btnDangNhap);
        btnDangKy=(Button)findViewById(R.id.btnDangKy);
        btnQuenMatKhau=(Button)findViewById(R.id.btnQuenMatKhau);
        btnDoiMk=(Button)findViewById(R.id.btnDoiMK);
    }
}