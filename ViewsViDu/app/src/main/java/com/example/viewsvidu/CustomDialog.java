package com.example.viewsvidu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CustomDialog extends AppCompatActivity {
    TextView txtLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_dialog);
        txtLogin=(TextView)findViewById(R.id.textViewLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogLogin();
            }
        });
    }
    private  void DialogLogin()
    {
        final Dialog dialog=new Dialog(CustomDialog.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//bỏ title của dialog, dòng này phải nằm trên dòng setContentView
        dialog.setContentView(R.layout.custom_dialog_layout);
        //dialog.setTitle("form đăng nhập");//title của dialog thì 1 số máy có 1 số máy ko có
        dialog.setCanceledOnTouchOutside(false);//true - mặc định: thì khi click ra ngoài dialog sẽ tự đóng dialog
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //dialog bằng 18/20 chiều rộng màn hình, 4/7 chiều cao màn hình
        dialog.getWindow().setLayout((18 * width)/20, (4* height)/7);

        final EditText edtUsername,edtPassword;
        Button btnDangnhap,btnHuy;
        edtUsername =(EditText) dialog.findViewById(R.id.edittextUserName);
        edtPassword=(EditText) dialog.findViewById(R.id.edittextPassWord);
        btnDangnhap=(Button) dialog.findViewById(R.id.btnDongY);
        btnHuy=(Button) dialog.findViewById(R.id.btnHuy);

        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=edtUsername.getText().toString().trim();
                String password=edtPassword.getText().toString().trim();
                if(username.equals("tinh") && password.equals("12345") )
                {
                    Toast.makeText(CustomDialog.this,"Đăng nhập thành công",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(CustomDialog.this,"Sai user hoặc mật khẩu",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  dialog.cancel(); //cách 1
                dialog.dismiss();  //cách 2
            }
        });
        dialog.show();
    }
}