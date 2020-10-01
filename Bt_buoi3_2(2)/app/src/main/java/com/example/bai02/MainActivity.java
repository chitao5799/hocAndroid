package com.example.bai02;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText txtBlock;
    int dem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBlock = findViewById(R.id.txtBlock);

        Button btnBlock = findViewById(R.id.btnBlock);
        btnBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra quyền
                checkPermission();
            }
        });
    }

    public class SMSReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            // Xử lý tin nhắn ở đây
            if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
                Bundle bundle = intent.getExtras();
                if (bundle != null) {
                    // get sms objects
                    Object[] pdus = (Object[]) bundle.get("pdus");
                    if (pdus.length == 0) {
                        return;
                    }
                    // large message might be broken into many
                    SmsMessage[] messages = new SmsMessage[pdus.length];
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < pdus.length; i++) {
                        messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        sb.append(messages[i].getMessageBody());
                    }
                    //String sender = messages[0].getOriginatingAddress();
                    String message = sb.toString();

                    // Gọi hàm xử lý gồm các việc:
                    // 1. Kiểm tra nội dung tin nhắn và tăng biến đếm (nếu cần)
                    // 2. Hiển thị thông báo lên màn hình
                    processSMS(message);
                    // prevent any other broadcast receivers from receiving broadcast
                    abortBroadcast();
                }
            }
        }
    }

    void processSMS(String message)
    {
        // Tachs chuỗi nhập của người dùng thành mảng
        String[] arr = txtBlock.getText().toString().split(",");

        // Lắp từ 1 đến hết và kiểm tra xem có trong tin nhắn không
        for (int i=0;i<arr.length;i++)
        {
            if (message.indexOf(arr[i])>=0)
            {
                // Có chứa keyword
                // Tăng biến đếm
                dem = dem +1;
                // Thông báo bằng Toast
                Toast.makeText(getApplicationContext(),
                        "Số tin nhắn chặn: " + dem,Toast.LENGTH_LONG).show();

                break;
            }
        }
    }

    void registerSMS()
    {
        // Đăng ký SMS Receiver. Khi có tin nhắn Android sẽ gọi ứng dụng của ta
        IntentFilter smsfilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
        registerReceiver(new SMSReceiver(), smsfilter);

    }

    void checkPermission()
    {
        // 1. Nếu là Android < Android M thì
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M )
        {
            // đăng ký Broadcast Receiver để nhận SMS
            registerSMS();
        }
        else  // 2. Nếu là Android >= Android M thì
        {
            // 2a. Kiểm tra xem có quyền nhận và đọc tin nhắn chưa?

            int has_sms_receive = checkSelfPermission(Manifest.permission.RECEIVE_SMS);
            int has_sms_read = checkSelfPermission(Manifest.permission.READ_SMS);

            // Khai bao list chứa quyền cần xin người dùng
            List<String> list_permission = new ArrayList<String>();

            // Kiểm tra xem đã cấp phét nhận tin nhắn chưa
            if (has_sms_receive != PackageManager.PERMISSION_GRANTED)
            {
                list_permission.add(Manifest.permission.RECEIVE_SMS);
            }

            // Kiểm tra xem đã cấp phép đọc tin nhắn chưa
            if (has_sms_read != PackageManager.PERMISSION_GRANTED)
            {
                list_permission.add(Manifest.permission.READ_SMS);
            }

            // Nếu có quyên cần xin
            if (!list_permission.isEmpty())
            {
                // Lệnh xin quyền
                requestPermissions(list_permission.toArray(new String[list_permission.size()]),
                        9999);
            }
            else // Nếu không có quyền gì cần xin
            {
                registerSMS();
            }

        }


        // Nếu có rồi thì Đăng ký Broadcast
        // Nếu chưa có thì yêu cầu người dùng cấp quyền
    }
}