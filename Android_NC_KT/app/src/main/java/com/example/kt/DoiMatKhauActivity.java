package com.example.kt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class DoiMatKhauActivity extends AppCompatActivity {
    EditText edtMkMoi,edtMkCu;
    Button btnThayDoi;
    TextView txtResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doi_mat_khau);

        edtMkMoi=(EditText)findViewById(R.id.edtMkMoi);
        edtMkCu=(EditText)findViewById(R.id.edtMkCu);
        btnThayDoi=(Button)findViewById(R.id.btnThayDoi);
        txtResult=(TextView)findViewById(R.id.txtMessageDoiMk);

        btnThayDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mkCu=edtMkCu.getText().toString().trim();
                String mkMoi=edtMkMoi.getText().toString().trim();
                if(mkCu.equals("")  || mkMoi.equals(""))
                    Toast.makeText(DoiMatKhauActivity.this,"Phải nhập mật khẩu cũ và mới",Toast.LENGTH_SHORT).show();
                else
                    doi_mat_khau(mkMoi,mkCu);
            }
        });
    }
    void doi_mat_khau(String mkMoi,String mkCu)
    {
        String webUrl = "https://miai.vn/sample/updatepass.php";

        // Create a task to download and display image.
        DoiMatKhau task = new DoiMatKhau(this.txtResult);

        // Execute task (Pass Url).
        task.execute(webUrl);

    }

    public class DoiMatKhau
            // AsyncTask<Params, Progress, Result>
            extends AsyncTask<String, Void, String> {

        private TextView txtResultMessageGeted;

        public DoiMatKhau(TextView txtInfo)  {
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
                httpConn.setRequestMethod("POST");
                String mkCu=edtMkCu.getText().toString().trim();
                String mkMoi=edtMkMoi.getText().toString().trim();
                String parameters = "old_pass=" + mkCu;
                parameters += "new_pass=" + mkMoi;


                OutputStreamWriter writer = new OutputStreamWriter(
                        httpConn.getOutputStream());
                writer.write(parameters);
                writer.flush();
                httpConn.connect();
              //  Log.w("123:",httpConn.getResponseCode()+"");
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