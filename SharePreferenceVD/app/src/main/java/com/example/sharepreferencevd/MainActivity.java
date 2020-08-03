package com.example.sharepreferencevd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    CheckBox cbRemember;
    EditText edtUserName,edtPassword;
    Button btnXacNhan;
    SharedPreferences sp;//nếu gỡ ứng dụng thì file được lưu cũng bị xóa bỏ
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=getSharedPreferences("dataLogin",MODE_PRIVATE);
        AnhXa();

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=edtUserName.getText().toString().trim();
                String passWord=edtPassword.getText().toString().trim();
                if(userName.equals("acc") && passWord.equals("123"))
                {
                    Toast.makeText(MainActivity.this,"Đăng nhập thành công",Toast.LENGTH_LONG).show();
                    if(cbRemember.isChecked())
                    {
                        SharedPreferences.Editor editor=sp.edit();
                        editor.putString("taikhoan",userName);
                        editor.putString("matkhau",passWord);
                        editor.putBoolean("checked",true);
                        editor.commit();
                    }
                    else{//chạy lại chương trình để thấy sự khác biệt
                        SharedPreferences.Editor editor=sp.edit();
                        editor.remove("taikhoan");//có thể lưu giá trị rỗng thay vì loại bỏ
                        editor.remove("matkhau");
                        editor.remove("checked");
                        editor.commit();
                    }
                }
                else{
                    Toast.makeText(MainActivity.this,"Đăng nhập thất bại",Toast.LENGTH_LONG).show();
                }
            }
        });
        //lấy giá trị từ share preference
        edtUserName.setText(sp.getString("taikhoan",""));
        edtPassword.setText(sp.getString("matkhau",""));
        cbRemember.setChecked(sp.getBoolean("checked",false));
    }

    private void AnhXa() {
        cbRemember=(CheckBox)findViewById(R.id.cbRemember);
        edtPassword=(EditText)findViewById(R.id.edtPassword);
        edtUserName=(EditText)findViewById(R.id.edtUserName);
        btnXacNhan=(Button)findViewById(R.id.btnXacNhan);
    }
}