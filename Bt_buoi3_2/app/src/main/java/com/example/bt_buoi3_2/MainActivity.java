package com.example.bt_buoi3_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//cho phép nhập các từ khóa, cách nhau bởi dấu phẩy. nhận tin nhắn đến xem tin nào có từ khóa 
//thì hiển thị nên listview và hiện thông báo có tin nhắn bị chặn
    EditText edtDSTuKhoa;
    ListView lvTinNhan;
    TextView tvTitle;
    ArrayList<String> listSmS=new ArrayList<>();
    ArrayAdapter adapter;

    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final int My_Permission_Request_receive_sms=0;

    ReceiverSMS my_receiver=new ReceiverSMS(){
        //nhấn ctrl + o để hiển gợi ý method override
        @Override
        public void onReceive(Context context, Intent intent) {
            super.onReceive(context, intent);
           // Toast.makeText(MainActivity.this,"message :"+message_received,Toast.LENGTH_SHORT).show();
          //  edtDSTuKhoa.setText(message_received);
           // tvTitle.setText(message_received);

            String[]  keyWord=edtDSTuKhoa.getText().toString().trim().split(",");
            for(int i=0;i<keyWord.length;i++)
            {
                if(message_received.toLowerCase().indexOf(keyWord[i].toLowerCase())>-1)
                {
                    Toast.makeText(MainActivity.this,"có tin nhắn bị chặn",Toast.LENGTH_LONG).show();
                    listSmS.add(message_received);
                    adapter.notifyDataSetChanged();
                    break;

                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(my_receiver,new IntentFilter(SMS_RECEIVED));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(my_receiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtDSTuKhoa=(EditText)findViewById(R.id.edtDSTuKhoa);
        lvTinNhan=(ListView)findViewById(R.id.lvDsTinNhan);
        tvTitle=(TextView)findViewById(R.id.tvTitle);
        adapter=new ArrayAdapter(MainActivity.this,android.R.layout.simple_list_item_1,listSmS);
        lvTinNhan.setAdapter(adapter);

        //check if the permission is not granted
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)!= PackageManager.PERMISSION_GRANTED)
        {
            //if the permission is not been granted then check if the user has denied the permission
            if(ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.RECEIVE_SMS))
            {
                //do nothing ass user ha denied
            }else{
                //a pop up will appear asking for required permission i.e allow  or deny
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.RECEIVE_SMS},My_Permission_Request_receive_sms );
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //will check the requestcode
        switch(requestCode)
        {

            case My_Permission_Request_receive_sms:
            {
                //check whether the length of grantResults is greater than 0 and is equal to permission_granted
                if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                    //now breadcastreceiver will work in background
                    Toast.makeText(this,"Thankyou for permission",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(this,"well i can't do anything until you permisson",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}