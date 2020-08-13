package com.example.volleyvd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Json_Object_Request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_object_request);

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json";
        /*
            {
                "monhoc" : "Lập trình Android" ,
                "noihoc" : "Trung Tâm Đào Tạo Tin Học Khoa Phạm",
                "website": "http://khoapham.vn",
                "fanpage": "http://facebook.com/khoapham.vn",
                "logo"   : "http://khoapham.vn/public/images/logo-370.png"
            }
        * */
        //JsonObjectRequest dùng đọc dữ liệu từ url mà dữ liệu là object json
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String monhoc=response.getString("monhoc");
                            Toast.makeText(Json_Object_Request.this, monhoc,Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Json_Object_Request.this, "lỗi",Toast.LENGTH_LONG).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}