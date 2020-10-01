package com.example.bt_buoi3_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
//cho phép nhập các từ khóa, cách nhau bởi dấu phẩy. nhận tin nhắn đến và xem tin nào có chứa từ khóa 
//thì hiển thị tin nhắn đó nên listview và hiện thông báo có tin nhắn bị chặn
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
                  //  Toast.makeText(MainActivity.this,"có tin nhắn bị chặn",Toast.LENGTH_LONG).show();
                    listSmS.add(message_received);
                    adapter.notifyDataSetChanged();

                    //  showNofi();
                    notification("my thông báo","có tin nhắn bị chặn",MainActivity.this);

                    break;
                }
            }
        }
    };
    private void showNofi(){

        // Tạo channel cho Ứng dụng
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // Channel ID đặt bất kì
        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "My Notifications", NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        // Trang trí cho thông báo
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);//,NOTIFICATION_CHANNEL_ID);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("Notification Alert, Click Me!");
        mBuilder.setContentText("Có tin nhắn bị chặn!");
        mBuilder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        mBuilder.setAutoCancel(true);

        ///
        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(MainActivity.this);
        notificationManagerCompat.notify(200,mBuilder.build());
        ///
//        /* Add Big View Specific Configuration */
//        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
//        String[] events = new String[6];
//        events[0] = new String("This is first line....");
//        events[1] = new String("This is second line...");
//        events[2] = new String("This is third line...");
//        events[3] = new String("This is 4th line...");
//        events[4] = new String("This is 5th line...");
//        events[5] = new String("This is 6th line...");
//// Sets a title for the Inbox style big view
//        inboxStyle.setBigContentTitle("Big Title Details:");
//// Moves events into the big view
//        for (int i=0; i < events.length; i++) {
//            inboxStyle.addLine(events[i]);
//        }
//        mBuilder.setStyle(inboxStyle);

        // Định nghĩa hành ododnjg sẽ thực hiện khi người dùng bấm Thông báo
        Intent resultIntent = new Intent( MainActivity.this, NotificationActivity.class);
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        resultIntent.putExtra("my_message","message của tôi");

        PendingIntent resultPendingIntent = PendingIntent.getActivity( MainActivity.this, 0,
                resultIntent, PendingIntent.FLAG_UPDATE_CURRENT );
       // mBuilder.setContentIntent(resultPendingIntent);

       // notificationManager.notify(0, mBuilder.build());
        ///
        AlarmManager alamManager= (AlarmManager) getSystemService(ALARM_SERVICE);
        long timeAtButtonClick=System.currentTimeMillis();
        long tenSecondInMili=1000*10;
        alamManager.set(AlarmManager.RTC_WAKEUP,timeAtButtonClick+tenSecondInMili,resultPendingIntent);
        ///
    }

    //nhấn ctrl + o để hiển gợi ý method override . Gõ ký tự trên bàn phím để tìm kiếm nếu cần
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

    public void notification(String title, String message, Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int notificationId = createID();
        String channelId = "channel-id";
        String channelName = "Channel Name";
        int importance = NotificationManager.IMPORTANCE_HIGH;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(
                    channelId, channelName, importance);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_baseline_chat_24)//R.mipmap.ic_launcher
                .setContentTitle(title)
                .setContentText(message)
                .setVibrate(new long[]{100, 250})
                .setLights(Color.YELLOW, 500, 5000)
                .setAutoCancel(true)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary));

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(new Intent(context, NotificationActivity.class));
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        notificationManager.notify(notificationId, mBuilder.build());
    }

    public int createID() {
        Date now = new Date();
        int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss", Locale.FRENCH).format(now));
        return id;
    }


}