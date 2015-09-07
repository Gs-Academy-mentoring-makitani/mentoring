package com.example.makitani.myproduct;

import android.app.Application;

/**
 * Created by tomoaki on 9/7/15.
 */
public class MyApplication extends Application {

    private static MyApplication sApp;

    public static String getsId() {
        return sId;
    }

    public static void setsId(String sId) {
        MyApplication.sId = sId;
    }

    private static String sId;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;

    }

    public static MyApplication getInstance(){
        return sApp;
    }
}
