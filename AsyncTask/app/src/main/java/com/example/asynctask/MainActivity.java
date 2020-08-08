package com.example.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnXuLy;
    TextView txtThongTin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnXuLy=(Button)findViewById(R.id.btnXuly);
        txtThongTin=(TextView)findViewById(R.id.txtThongTin);

        btnXuLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CongViec().execute();
            }
        });
    }

    /**
     * AsyncTask<params,progress,result>
     *     params là khi gọi class CongViec có muốn truyền tham số gì không
     *     progress là quá trình xử lý nó trả về tham số kiểu gì
     *     result là kết quả trả ra kiểu gì
     */
    private  class CongViec extends AsyncTask<Void,String,String>
    {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            txtThongTin.setText("Bắt đầu "+"\n");
        }

        @Override
        protected String doInBackground(Void... voids) {
            for(int i=1;i<=5;i++)
            {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
              //  txtThongTin.append("xong công việc "+i+"\n");//lỗi, vì không được tác động làm thay đổi giao diện trong này.
                publishProgress("xong công việc "+i,"\n");
            }
            return "xong rồi. \n";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            txtThongTin.append(s +" - kết quả nhận được trong onPostExcute");
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            txtThongTin.append(values[0]+values[1]);
        }
    }
}