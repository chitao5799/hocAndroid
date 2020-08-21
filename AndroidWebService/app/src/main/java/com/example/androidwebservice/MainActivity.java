package com.example.androidwebservice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionSession;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
   // String urlGetData="http://192.168.0.104:8080/HocAndroidWebservice/getdata.php"; //localhost
    String urlGetData="http://webservice-example.epizy.com/getdata.php";  //online
    //String urlDelete="http://192.168.0.104:8080/HocAndroidWebservice/delete.php";
    String urlDelete="http://webservice-example.epizy.com/delete.php";
    ListView listViewSV;
    SinhVienAdapter adapter;
    ArrayList<SinhVien> arraySV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewSV=(ListView)findViewById(R.id.listviewSV);
        arraySV=new ArrayList<>();
        adapter=new SinhVienAdapter(MainActivity.this,R.layout.dong_sing_vien,arraySV);
        listViewSV.setAdapter(adapter);

        GetData(urlGetData);
    }
    private void GetData(String url)
    {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        //dữ liệu lấy được về là mảng các object sinh viên
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arraySV.clear();
                       for(int i=0;i<response.length();i++)
                       {
                           try {
                               JSONObject object=response.getJSONObject(i);
                               arraySV.add(new SinhVien(
                                       object.getInt("ID"),
                                       object.getString("HoTen"),
                                       object.getInt("NamSinh"),
                                       object.getString("DiaChi")
                               ));

                           } catch (JSONException e) {
                               e.printStackTrace();
                           }

                       }
                       adapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
    public void DeleteStudent(final int idsv)
    {
        RequestQueue requestQueue=Volley.newRequestQueue(this);
        StringRequest stringRequest=new StringRequest(
                Request.Method.POST,
                urlDelete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.toString().trim().equals("xoa thanh cong"))
                        {
                            Toast.makeText(MainActivity.this,"Xóa thành công",Toast.LENGTH_SHORT).show();
                            GetData(urlGetData);
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Xóa thất bại",Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,"xảy ra lỗi",Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
                params.put("idSinhVien",String.valueOf(idsv));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_student,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menuAddStudent)
        {
            startActivity(new Intent(MainActivity.this,AddStudentActivity.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}