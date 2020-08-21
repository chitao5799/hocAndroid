package com.example.androidwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AddStudentActivity extends AppCompatActivity {
    EditText edtHoTen,edtNamSinh,edtDiaChi;
    Button btnThem,btnHuy;
    //String urlInsert="http://192.168.0.104:8080/HocAndroidWebservice/insert.php";  //localhost
    String urlInsert="http://webservice-example.epizy.com/insert.php";  //online
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        AnhXa();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten=edtHoTen.getText().toString().trim();
                String namsinh=edtNamSinh.getText().toString().trim();
                String diachi=edtDiaChi.getText().toString().trim();
                if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty())
                    Toast.makeText(AddStudentActivity.this,"vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                else
                    AddSV(urlInsert);
            }
        });
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddStudentActivity.this,MainActivity.class));
                finish();
            }
        });
    }
    private void AddSV(String url)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("thanh cong"))
                        {
                            Toast.makeText(AddStudentActivity.this,"thêm sinh viên thành công",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(AddStudentActivity.this ,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(AddStudentActivity.this,"thêm sinh viên thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddStudentActivity.this,"đã xảy ra lỗi",Toast.LENGTH_SHORT).show();
                        Log.d("myLog","lỗi: "+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("hotenSV",edtHoTen.getText().toString().trim());
                params.put("namsinhSV",edtNamSinh.getText().toString().trim());
                params.put("diachiSV",edtDiaChi.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(request);
    }

    @Override
    public void onBackPressed() {
        //khi chuyển từ activity này sang activity khác thì finish activity trước đó nên phải
        // start activity khi nhấn nút back
        startActivity(new Intent(AddStudentActivity.this,MainActivity.class));
        finish();
    }

    private void AnhXa() {
        edtHoTen=(EditText)findViewById(R.id.edtHoTen);
        edtNamSinh=(EditText)findViewById(R.id.edtNamSinh);
        edtDiaChi=(EditText)findViewById(R.id.edtDiaChi);
        btnHuy=(Button)findViewById(R.id.btnHuy);
        btnThem=(Button)findViewById(R.id.btnThem);
    }
}