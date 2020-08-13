package com.example.json_object;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonArray extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_array);

        new ReadJson().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json");
        /*
        {
        "danhsach": [
                  { "khoahoc" : "Lập trình Android" },
                  { "khoahoc" : "Lập trình iOS" },
                  { "khoahoc" : "Lập trình PHP" },
                  { "khoahoc" : "Lập trình NodeJS" },
                  { "khoahoc" : "Lập trình Unity" }
                ]
        }
       */
    }
    private class ReadJson extends AsyncTask<String,Void,String>
    {
        StringBuilder content=new StringBuilder();
        @Override
        protected String doInBackground(String... strings)
        {
            try {
                URL url=new URL(strings[0]);
                InputStreamReader inputStreamReader=new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line="";
                while((line=bufferedReader.readLine())!=null)
                {
                    content.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject jsonObject=new JSONObject(s);

                JSONArray jsonArray=jsonObject.getJSONArray("danhsach");

                String name="";
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject object=jsonArray.getJSONObject(i);
                    name+=object.getString("khoahoc")+"\n";
                }
                Toast.makeText(JsonArray.this, name,Toast.LENGTH_LONG).show();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}