package com.example.androidnodejs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


/*
* thư viện android socketio: https://github.com/socketio/socket.io-client-java
*
*/
public class MainActivity extends AppCompatActivity {
    Socket mSocket; //dùng của thư viện io.socket.client
    TextView txtTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtTest=(TextView)findViewById(R.id.txtTest);
        //vd gửi nhận dữ liệu giữa app android và server nodejs, chạy file test.js trong folder ServerNodeJS
        try {
            mSocket= IO.socket("http://192.168.0.104:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();

        mSocket.on("server-send-data",onRetrieveData);
        mSocket.emit("client-sent-data","android gui data");
    }
    private Emitter.Listener onRetrieveData =new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject= (JSONObject) args[0];
                    try {
                        String ten=jsonObject.getString("noidung");
                        txtTest.setText(ten);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
}