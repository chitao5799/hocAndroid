package com.example.bai03;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/*
-Màn hình A hiển thị 1 combobox gồm danh sách các Nước trên thế giới ( Dữ liệu load từ URL
https://api.covid19api.com/countries) và 2 button “Xem thông tin” và “Cập nhật realtime”
-Nếu người dùng chọn 1 nước trong combo và bấm “Xem thông tin”, ứng dụng hiển thị thông tin về tổng số ca
nhiễm, số ca khỏi bệnh và số ca tử vong tại nước đó. Dữ liệu có thể lấy tại
https://api.covid19api.com/summary và lọc riêng thông tin nước người dùng chọn.
-Nếu người dùng bấm “Cập nhật realtime”, ứng dụng chuyển sang Màn hình B. Màn hình này liên tục truy cập
Url nói trên và hiển thị thông tin về tổng số ca nhiễm, số ca khỏi bệnh, số ca tử vọng mỗi 1 phút.
-Lưu ý: Tổng số ca nhiễm là TotalConfirmed, tổng số ca khỏi bệnh là: TotalRecovered, tổng số ca tử vong là
TotalDeaths
*/
public class MainActivity extends AppCompatActivity {

    Spinner spnCountry;
    ArrayAdapter countryAdapter;
    TextView txtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Khởi tạo Adapter
        countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);

        // Link Spiner vào Adapter
        spnCountry = findViewById(R.id.spnCountry);
        spnCountry.setAdapter(countryAdapter);

        txtInfo = findViewById(R.id.txtInfo);

        Button btnView = findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                get_country_info();
            }
        });
        // Check quyền truy cập internet
        checkPermission();
    }

    void checkPermission()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int has_internet = checkSelfPermission(Manifest.permission.INTERNET);

            List<String> permissions = new ArrayList<>();

            if (has_internet != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.INTERNET);
            }

            if (!permissions.isEmpty()) {
                requestPermissions(permissions.toArray(new String[permissions.size()]),
                        9999);
            }
            else
            {
                // Lấy dữ liệu từ API vào spiner Country
                call_get_country();
            }
        }
        else {
            // Lấy dữ liệu từ API vào spiner Country
            call_get_country();
        }
    }

    void get_country_info()
    {
        String webUrl = "https://api.covid19api.com/summary";

        // Create a task to download and display image.
        GetCountryInfo task = new GetCountryInfo(this.txtInfo);

        // Execute task (Pass imageUrl).
        task.execute(webUrl);
    }

    public class GetCountryInfo
            // AsyncTask<Params, Progress, Result>
            extends AsyncTask<String, Void, String> {

        private TextView l_txtInfo;

        public GetCountryInfo(TextView txtInfo)  {
            this.l_txtInfo= txtInfo;
        }

        @Override
        protected String doInBackground(String... params) {

            String textUrl = params[0];

            InputStream in = null;
            BufferedReader br= null;
            try {
                URL url = new URL(textUrl);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                int resCode = httpConn.getResponseCode();

                if (resCode == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();
                    br= new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb= new StringBuilder();
                    String s;
                    while((s= br.readLine())!= null) {
                        sb.append(s);
                        sb.append("\n");
                    }
                    return sb.toString();
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
            //return null;
        }

        @Override
        protected void onPostExecute(String result) {
            if(result  != null){
                try {

                    JSONObject jobj = new JSONObject(result);
                    JSONArray jarr = jobj.getJSONArray("Countries");
                    for (int i=0;i<jarr.length();i++)
                    {
                        JSONObject o = jarr.getJSONObject(i);
                        if (o.getString("Country").equals(spnCountry.getSelectedItem().toString()))
                        {

                            l_txtInfo.setText("Tổng số ca nhiễm : " + o.getString("TotalConfirmed") + "\nTổng số ca tư vong: " + o.getString("TotalDeaths") +   "\nTổng số ca bình phục: " + o.getString("TotalRecovered"));
                            break;
                        }
                    }
                    //l_countryAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    Log.e("E", e.toString());
                }


                //this.textView.setText(result);
            } else{
                Log.e("MyMessage", "Failed to fetch data!");
            }
        }
    }


    void call_get_country()
    {
        // Tạo mới 1 luồng con
        get_country_list task = new get_country_list(this.countryAdapter);

        // Truyền vào URL API
        task.execute("https://api.covid19api.com/countries");
    }
    public class get_country_list  extends AsyncTask<String, Void, String> {

        private ArrayAdapter<String> l_countryAdapter ;
        // 1. Hàm tạo
        public get_country_list(ArrayAdapter<String> spnCountry)
        {
            this.l_countryAdapter = spnCountry;
        }

        // 2. Xử lý nền (do in background)
        @Override
        protected String doInBackground(String... params) {
            String textUrl = params[0];

            InputStream in = null;
            BufferedReader br= null;
            try {
                URL url = new URL(textUrl);
                HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

                httpConn.setAllowUserInteraction(false);
                httpConn.setInstanceFollowRedirects(true);
                httpConn.setRequestMethod("GET");
                httpConn.connect();

                int resCode = httpConn.getResponseCode();

                if (resCode == HttpURLConnection.HTTP_OK) {
                    in = httpConn.getInputStream();
                    br= new BufferedReader(new InputStreamReader(in));

                    StringBuilder sb= new StringBuilder();
                    String s;
                    while((s= br.readLine())!= null) {
                        sb.append(s);
                        sb.append("\n");
                    }
                    return sb.toString();
                } else {
                    return null;
                }
            } catch (Exception e) {
                return null;
            }
        }

        // 3. Sau khi nhận đủ dữ liệu từ Server
        @Override
        protected void onPostExecute(String result) {
            // Xử lý result, chuyển thành JSON
            if (result!=null)
            {
                // Convert từ string sang JSON Array
                try {
                    JSONArray arr = new JSONArray(result);
                    // Lặp và thêm vào ArrayAdpter
                    l_countryAdapter.clear();
                    for (int i=0;i<arr.length();i++)
                    {
                        // Lấy ra JSON object tại vị trí i
                        JSONObject objcountry = arr.getJSONObject(i);
                        // Thêm tên nước vào ArrayAdapter
                        l_countryAdapter.add(objcountry.getString("Country"));
                    }

                    l_countryAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            else
            {
                // Nothing
            }

            // Cập nhật Spinner
        }
    }
}