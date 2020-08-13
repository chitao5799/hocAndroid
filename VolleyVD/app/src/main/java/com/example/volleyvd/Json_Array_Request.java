package com.example.volleyvd;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Json_Array_Request extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json__array__request);

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        String url="https://khoapham.vn/KhoaPhamTraining/json/tien/demo4.json";
        /*
                [
                  {"khoahoc"  :  "Lập trình Android"  , "hocphi" : "4000000"},
                  {"khoahoc"  :  "Lập trình iOS", "hocphi" : "4500000"},
                  {"khoahoc"  :  "Lập trình PHP"   , "hocphi" : "4000000"},
                  {"khoahoc"  :  "Lập trình NodeJS"    , "hocphi" : "3000000"},
                  {"khoahoc"  :  "Lập trình Unity"     , "hocphi" : "3500000"}
                ]
        * */
        //JsonArrayRequest đọc dữ liệu từ url mà dữ liệu là 1 array.
        JsonArrayRequest jsonArrayRequest =  new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject object=response.getJSONObject(i);
                                String mon=object.getString("khoahoc");
                                String gia=object.getString("hocphi");
                                Toast.makeText(Json_Array_Request.this,mon+" - "+gia,Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}