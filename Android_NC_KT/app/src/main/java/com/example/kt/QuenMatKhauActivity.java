package com.example.kt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class QuenMatKhauActivity extends AppCompatActivity {
    EditText edtEmail;
    Button btnCapLaiMaiKhau;
    TextView txtResultMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quen_mat_khau);

        edtEmail=(EditText)findViewById(R.id.edtEmailQuenMK);
        btnCapLaiMaiKhau=(Button)findViewById(R.id.btnCapLaiMatKhau);
        txtResultMessage=(TextView)findViewById(R.id.txtResultMessage);

        btnCapLaiMaiKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=edtEmail.getText().toString().trim();
                if (email.equals(""))
                    Toast.makeText(QuenMatKhauActivity.this,"Email không hợp lệ",Toast.LENGTH_SHORT).show();
                else
                {
                    quen_mat_khau(email);
                }
            }
        });
    }
    void quen_mat_khau(String email)
    {
        String webUrl = "https://miai.vn/sample/resetpass.php?email="+email;

        // Create a task to download and display image.
        QuenMatKhau task = new QuenMatKhau(this.txtResultMessage);

        // Execute task (Pass Url).
        task.execute(webUrl);

    }

    public class QuenMatKhau
            // AsyncTask<Params, Progress, Result>
            extends AsyncTask<String, Void, String> {

        private TextView txtResultMessageGeted;

        public QuenMatKhau(TextView txtInfo)  {
            this.txtResultMessageGeted= txtInfo;
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
                    String resultMessage=jobj.getString("result_message").toString();

                    txtResultMessageGeted.setText(resultMessage);
                    //txtResultMessage.setText(result);


                } catch (JSONException e) {
                    Log.e("E", e.toString());
                }


                //this.textView.setText(result);
            } else{
                Log.e("MyMessage", "Failed to fetch data!");
            }
        }
    }

}