package com.example.takenogaku.notification;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.takenogaku.helloworld.MainActivity;
import com.example.takenogaku.helloworld.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.util.Iterator;

//import com.example.gsacademy.notification.GcmBroadcastReceiver;



/**
 * Created by makitani on 2015/08/08.
 */
public class GcmIntentService extends IntentService {

    public static final int NOTIFICATION_ID = 1;
    private NotificationManager mNotificationManager;
    NotificationCompat.Builder builder;

    public GcmIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
        String messageType = gcm.getMessageType(intent);
        Resources res = getResources();

        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                Log.d("LOG","messageType(error): " + messageType + ",body:" + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                Log.d("LOG","messageType(deleted): " + messageType + ",body:" + extras.toString());
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                Log.d("LOG","messageType(message): " + messageType + ",body:" + extras.toString());

                Iterator<String> it = extras.keySet().iterator();
                String key;
                String message = "";
                String title = "";
                while(it.hasNext()) {
                    key = it.next();
                    if (key.equals("message") == true) {
                        message = extras.getString(key);
                    }

                    if (key.equals("title") == true) {
                        title = extras.getString(key);
                    }

                }

                //通知バーに表示
                sendNotification(message,title);
            }
        }
        GcmBroadCastReceiver.completeWakefulIntent(intent);


    }

    private void sendNotification(String msg,String title) {
        mNotificationManager = (NotificationManager)
                this.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, MainActivity.class), 0);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.my_icon)
                        .setContentTitle(title)
                        .setStyle(new NotificationCompat.BigTextStyle()
                                .bigText(msg))
                        .setContentText(msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }


}
