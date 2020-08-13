package com.example.json_object;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONLanguage extends AppCompatActivity {
    ImageButton btnVietName,btnEnglish;
    TextView tvThongTin;
    String noiDung="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_language);

        btnVietName=(ImageButton)findViewById(R.id.imgBtnVietnam);
        btnEnglish=(ImageButton)findViewById(R.id.imgBtnEnglish);
        tvThongTin=(TextView)findViewById(R.id.tvThongTin);

        new ReadJson().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo3.json");
        /*
        {
          "language": {
                    "en": {
                              "name":"Khoa Pham Training",
                              "address":"90 - 92 Le Thi Rieng",
                              "course1":"Android",
                              "course2":"PHP",
                              "course3":"iOS"
                            },
                    "vn": {
                              "name":"Trung tâm đào tạo tin học Khoa Phạm",
                              "address":"Số 90 - 92 Lê Thị Riêng",
                              "course1":"Lập trình Android",
                              "course2":"Lập trình PHP",
                              "course3":"Lập trình iOS"
                            }
                  }
        }
         */

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDuLieu("en");
            }
        });
        btnVietName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDuLieu("vn");
            }
        });
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

            noiDung=s;
            getDuLieu("vn");
        }
    }
    private  void getDuLieu(String lang)
    {
        try {
            JSONObject jsonObject=new JSONObject(noiDung);

            JSONObject objectLanguage=jsonObject.getJSONObject("language");

            JSONObject objectVN=objectLanguage.getJSONObject(lang);

            String ten=objectVN.getString("name");
            String diachi=objectVN.getString("address");
            String khoahoc1=objectVN.getString("course1");
            String khoahoc2=objectVN.getString("course2");
            String khoahoc3=objectVN.getString("course3");

            tvThongTin.setText(ten+"\n"+diachi+"\n"+khoahoc1+"\n"+khoahoc2+"\n"+khoahoc3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}