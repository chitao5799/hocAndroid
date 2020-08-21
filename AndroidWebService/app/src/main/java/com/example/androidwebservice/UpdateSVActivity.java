package com.example.androidwebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class UpdateSVActivity extends AppCompatActivity {
    EditText edtHoten,edtNamsinh,edtDiachi;
    Button btnUpdate,btnHuy;
    int id=0;
   // String urlUpdate="http://192.168.0.104:8080/HocAndroidWebservice/update.php";  //localhost
    String urlUpdate="http://webservice-example.epizy.com/update.php"; //online
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_sv);

        AnhXa();

        Intent intent=getIntent();
        SinhVien sinhVien= (SinhVien) intent.getSerializableExtra("dataSinhVien");

        id=sinhVien.getId();
        edtHoten.setText(sinhVien.getHoTen());
        edtNamsinh.setText(sinhVien.getNamSinh()+"");
        edtDiachi.setText(sinhVien.getDiaChi());

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UpdateSVActivity.this,MainActivity.class));
                finish();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hoten=edtHoten.getText().toString().trim();
                String namsinh=edtNamsinh.getText().toString().trim();
                String diachi=edtDiachi.getText().toString().trim();
                if(hoten.isEmpty() || namsinh.isEmpty() || diachi.isEmpty())
                    Toast.makeText(UpdateSVActivity.this,"vui lòng nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                else
                    UpdateSV(urlUpdate);
            }
        });
    }
    private void UpdateSV(String url)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("cap nhat thanh cong"))
                        {
                            Toast.makeText(UpdateSVActivity.this,"cập nhật sinh viên thành công",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(UpdateSVActivity.this ,MainActivity.class));
                            finish();
                        }else{
                            Toast.makeText(UpdateSVActivity.this,"cập nhật sinh viên thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateSVActivity.this,"đã xảy ra lỗi",Toast.LENGTH_SHORT).show();
                        Log.d("myLog","lỗi: "+error.toString());
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String,String> params=new HashMap<>();
                params.put("id_SV",String.valueOf(id));
                params.put("hoten_SV",edtHoten.getText().toString().trim());
                params.put("namsinh_SV",edtNamsinh.getText().toString().trim());
                params.put("diachi_SV",edtDiachi.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void AnhXa() {
        edtHoten=(EditText)findViewById(R.id.edtHoTenUpdate);
        edtNamsinh=(EditText)findViewById(R.id.edtNamSinhUpdate);
        edtDiachi=(EditText)findViewById(R.id.edtDiaChiUpdate);
        btnUpdate=(Button)findViewById(R.id.btnCapNhat);
        btnHuy=(Button)findViewById(R.id.btnHuyUpdate);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UpdateSVActivity.this,MainActivity.class));
        finish();
    }
}