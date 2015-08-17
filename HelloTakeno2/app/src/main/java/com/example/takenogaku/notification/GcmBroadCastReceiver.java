package com.example.takenogaku.notification;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

    /**
 * Created by makitani on 2015/08/08.
 */
public class GcmBroadCastReceiver extends WakefulBroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {

            // Explicitly specify that GcmMessageHandler will handle the intent.
            ComponentName comp = new ComponentName(context.getPackageName(),
                    GcmIntentService.class.getName());

            // Start the service, keeping the device awake while it is launching.
            startWakefulService(context, (intent.setComponent(comp)));
            setResultCode(Activity.RESULT_OK);

            String msg = intent.getStringExtra("message");
            String title = intent.getStringExtra("title");

            Intent i = new Intent(context, AlertDialogActivity.class);
            i.putExtra("message", msg + "");
            i.putExtra("title",title+"");

            //PendingIntent を作るところで PendingIntent.FLAG_UPDATE_CURRENT
            //というフラグを指定してあげないと、古いものが再利用されるらしい。
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
            try {

                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
        }

    }
