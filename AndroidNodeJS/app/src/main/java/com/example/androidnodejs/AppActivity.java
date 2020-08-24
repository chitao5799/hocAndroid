package com.example.androidnodejs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;
import java.util.ArrayList;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class AppActivity extends AppCompatActivity {
    Socket mSocket; //dùng của thư viện io.socket.client
    ListView listUser,listChat;
    EditText edtInput;
    ImageView btnAddUser,btnSendChat;

    ArrayList<String> arrUser,arrChat;
    ArrayAdapter adapterUser,adapterChat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        //chạy file index.js (server) trong folder ServerNodeJS
        try {
            mSocket= IO.socket("http://192.168.0.104:3000/");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.connect();
        AnhXa();

        mSocket.on("server-send-result",onRetrieveResult);
        mSocket.on("server-send-list-user",onGetListUser);
        mSocket.on("server-send-chat",onGetMessageChat);

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSocket.emit("client-register-user",edtInput.getText().toString());
                edtInput.setText("");
            }
        });
        btnSendChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSocket.emit("client-send-chat",edtInput.getText().toString());
                edtInput.setText("");
            }
        });
    }
    private Emitter.Listener onRetrieveResult =new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject= (JSONObject) args[0];
                    try {
                        boolean exists=jsonObject.getBoolean("ketqua");
                        if(exists)
                            Toast.makeText(AppActivity.this,"user này đã tồn tại",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(AppActivity.this,"đăng ký thành công",Toast.LENGTH_LONG).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private Emitter.Listener onGetListUser =new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject= (JSONObject) args[0];
                    arrUser.clear();
                    try {
                        JSONArray arrUserName=jsonObject.getJSONArray("danhsach");
                        for(int i=0;i<arrUserName.length();i++)
                        {
                            arrUser.add(arrUserName.getString(i));
                        }
                        adapterUser.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private Emitter.Listener onGetMessageChat =new Emitter.Listener() {
        @Override
        public void call(final Object... args) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    JSONObject jsonObject= (JSONObject) args[0];
                    try {
                        String message=jsonObject.getString("contentsChat");
                        arrChat.add(message);
                        adapterChat.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    };
    private void AnhXa() {
        listChat=(ListView)findViewById(R.id.listChat);
        listUser=(ListView)findViewById(R.id.listUser);
        edtInput=(EditText)findViewById(R.id.edtInput);
        btnAddUser=(ImageView)findViewById(R.id.imgAddUser);
        btnSendChat=(ImageView)findViewById(R.id.imgSend);

        arrUser=new ArrayList<>();
        adapterUser=new ArrayAdapter(AppActivity.this,android.R.layout.simple_list_item_1,arrUser);
        listUser.setAdapter(adapterUser);

        arrChat=new ArrayList<>();
        adapterChat=new ArrayAdapter(AppActivity.this,android.R.layout.simple_list_item_1,arrChat);
        listChat.setAdapter(adapterChat);
    }
}