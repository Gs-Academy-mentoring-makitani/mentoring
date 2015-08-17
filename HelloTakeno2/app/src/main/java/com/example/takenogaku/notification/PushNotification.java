package com.example.takenogaku.notification;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

import static android.util.Log.v;

/**
 * Created by makitani on 2015/08/08.
 */
public class PushNotification {
    private AsyncTask<Void, Void, String> registtask = null;
    private GoogleCloudMessaging gcm;
    private String regid = "";
    private Context context;

    public static final String SENDER_ID = "xxxxxx";
    public final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;

    public PushNotification(Context con) {
        this.context = con;
    }

    public void pushInit() {
        // Play serviceが有効かチェック
        if (checkPlayServices()) {
            gcm = GoogleCloudMessaging.getInstance(context);
            regist_id();
        }
    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, (Activity) context,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            }
            return false;
        }
        return true;
    }


    private void regist_id() {
        registtask = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(context);
                }
                try {
                    //GCMサーバーへ登録
                    regid = gcm.register(SENDER_ID);
                    v("registration_id",regid);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(String result) {
                registtask = null;
            }
        };
        registtask.execute(null, null, null);

    }



}
