package com.example.bt_buoi3_2;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceiverSMS extends BroadcastReceiver {
    private static final String SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG="SmsBroadcastReceiver";
    String message_received="",phoneNumber="";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"intent received: "+intent.getAction());
        // Nếu là Tin nhắn mới
        if (intent.getAction().equals(SMS_RECEIVED)) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                // get sms objects
                Object[] pdus = (Object[]) bundle.get("pdus");
                if (pdus.length == 0) {
                    return;
                }
                // large message might be broken into many
                SmsMessage[] messages = new SmsMessage[pdus.length];
          /*      StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pdus.length; i++) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                    sb.append(messages[i].getMessageBody());
                }
                String sender = messages[0].getOriginatingAddress();
                message_received = sb.toString();

                // Hiển thị Toast tin nhắn trên màn hình
             //   Toast.makeText(context, message_received, Toast.LENGTH_LONG).show();

                // prevent any other broadcast receivers from receiving broadcast
                abortBroadcast();*/
                for(int i=0;i<pdus.length;i++)
                {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                    {
                        String format=bundle.getString("format");
                        messages[i]=SmsMessage.createFromPdu((byte[])pdus[i],format);

                    }else
                    {
                        messages[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                    }
                    message_received=messages[i].getMessageBody();
                    phoneNumber=messages[i].getOriginatingAddress();
                }
                //Toast.makeText(context,"message get in broastcast: "+message_received+" phone number:"+phoneNumber,Toast.LENGTH_SHORT).show();


            }
        }
    }
}
