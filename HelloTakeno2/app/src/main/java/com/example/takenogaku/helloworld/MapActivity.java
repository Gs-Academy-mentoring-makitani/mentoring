package com.example.takenogaku.helloworld;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import static android.util.Log.v;


public class MapActivity extends ActionBarActivity {

    protected void onCreate() {
        String sUrl = "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + 35.720625 + "," + 139.757405 + "&language=ja&sensor=true";
        Thread threadNews = new Thread(new MythreadGetListData(sUrl));
        threadNews.start();
    }

    private class MythreadGetListData implements Runnable {

        private String responce_json;
        private String url = "";
        private String address = "";
        //MyThread用-----------------------------------
        protected Handler mHandlerLatLng = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                v("api_responce", "::::" + (String) msg.obj + ":");

            }
        };

        public MythreadGetListData(String url) {
// TODO 自動生成されたコンストラクター・スタブ
            this.url = url;
        }

        public void run() {
            responce_json = doGet(this.url);
     //       HttpRequest hr = new HttpRequest();
       //     responce_json = hr.doGet(this.url);

// ハンドラにメッセージを通知
            mHandlerLatLng.sendEmptyMessage(0);
            mHandlerLatLng.post(new Runnable() {

                public void run() {

                    try {

                        if (responce_json != null) {

                            JSONObject addr_json = new JSONObject(responce_json);
                            JSONArray addr_arg = addr_json.getJSONArray("results");
                            JSONObject addr = addr_arg.getJSONObject(0);
                            address = addr.getString("formatted_address");

                            Message msg = Message.obtain(); //推奨
                            msg.obj = new String(address);
                            mHandlerLatLng.sendMessage(msg);
                          //  addressView.setText((String) msg.obj);
                            v("_reponce_map",responce_json+"");

                        }

                    } catch (JSONException e) {
// TODO 自動生成された catch ブロック
                        e.printStackTrace();

                    }

                }

            });

        }



    }

    public String doGet(String urlpath) {

        String result = "";

        HttpURLConnection conn = null;
        try {

            URL url = new URL(urlpath);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int resp = conn.getResponseCode();
// respを使っていろいろ
            result = readIt(conn.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if(conn != null) {
                conn.disconnect();
            }
        }
        return result;
    }

    public String readIt(InputStream stream) throws IOException, UnsupportedEncodingException {
        StringBuffer sb = new StringBuffer();
        String line = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        while((line = br.readLine()) != null){
            sb.append(line);
        }
        try {
            stream.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
