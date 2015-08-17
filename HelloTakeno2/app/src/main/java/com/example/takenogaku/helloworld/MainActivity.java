package com.example.takenogaku.helloworld;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.example.takenogaku.lib.PersonOpenHelper;
import com.example.takenogaku.lib.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.util.Log.v;


public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase dbObj;

    private int dispWidth;

    private int dispHeight;

    private  Button edit_profile;
    private  Button new_profile;
    private  Button prof_list;
    private  Button maptest;
    private  Button jasontest;
    private  Button sexView;
    private TextView nameView;
    private Button nationView;
    private TextView addressView;
    private TextView emailView;

    private LinearLayout friend_listlView;
    private LinearLayout underView;
    private LinearLayout fr_ttlView;
    private ImageView my_iconView;
    private LinearLayout hs;

    private  Button test_list;

    private String name = "";
    private String email = "";
    private String address = "";
    private int sex = 0;
    private String nation = "";


    private ArrayList<Map<String, Object>> userData;
    private ArrayList<byte[]> friendIconData;
    private Uri photoUri;



    private PersonOpenHelper dbHelper;
//    private Resources resource;

    private Util u;

    private int user_id = 0;
    private  byte[] user_icon = null;



    private View.OnClickListener submit_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            submit_Click(v);
        }
    };

    private View.OnClickListener new_submit_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {

            new_submit_Click(v);

        }
    };


    private View.OnClickListener iconChg_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
//            commonImageTrans(MainActivity.this);
            moveToEditAct();

        }
    };



    private View.OnClickListener prof_list_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            prof_list_Click();
        }
    };

    private View.OnClickListener test_list_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            test_list_Click();
        }
    };


    private View.OnClickListener maptest_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            maptest_Click();

        }
    };

    private View.OnClickListener jasontest_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            jasontest_Click();

        }
    };


    private final int FP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new PersonOpenHelper(this);
        u = new Util();

        userData = new ArrayList<Map<String, Object>>();

        friendIconData = new ArrayList<>();

        setViewObject();
        setListner();
        setYourProfile();
        depProfileData();

        // 画像取得処理
        String imageUrl = "http://http://s3-ap-northeast-1.amazonaws.com/milestone-b2b/90e9cf8547517cf23d9dbfd66571cf1e75515296/000570021_01.jpg";
        ImageView cover_imgView = (ImageView)findViewById(R.id.coverimageView);
        BitmapCache bmcache = new BitmapCache();
        RequestQueue rqQueue = Volley.newRequestQueue(this);
        ImageLoader ilImage = new ImageLoader(rqQueue, bmcache);
//キャッシュを取得する
        Cache c = rqQueue.getCache();
//キャッシュのエントリを取得する。
//画像のurlパスがキャッシュデータを格納する配列のキーとなっている。
        Cache.Entry b = c.get(imageUrl);
        Bitmap bm = null;
//キャッシュがない場合、ネットワークから画像をロードする。
        if (b == null) {
            ImageLoader.ImageListener listener = ImageLoader.getImageListener(cover_imgView, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
//画像のurlパスをキャッシュデータを格納する配列のキーとなる。
            ilImage.get(imageUrl, listener);
            v("volley","2");
        } else {
            byte[] byteArray = b.data;
//byte[] からビットマップを取得する
            bm = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            cover_imgView.setImageBitmap(bm);
            v("volley","1");
        }

    }


    /**
     */
    private void setViewObject () {
        edit_profile = (Button)findViewById(R.id.edit_profile);

        new_profile = (Button)findViewById(R.id.new_profile);


        prof_list = (Button)findViewById(R.id.prof_list);
        test_list = (Button)findViewById(R.id.test_list);
        maptest = (Button)findViewById(R.id.maptest);
        jasontest = (Button)findViewById(R.id.jasontest);
        my_iconView = (ImageView)findViewById(R.id.my_icon);
        nameView  = (TextView)findViewById(R.id.name);
        sexView = (Button)findViewById(R.id.sex);
        nationView = (Button)findViewById(R.id.nation);
        addressView  = (TextView)findViewById(R.id.address);
        emailView  = (TextView)findViewById(R.id.email);
        friend_listlView  = (LinearLayout)findViewById(R.id.friend_list);
        underView  = (LinearLayout)findViewById(R.id.underview);
        fr_ttlView  = (LinearLayout)findViewById(R.id.fr_ttl);

        hs = (LinearLayout)findViewById(R.id.scrollerhrz);

    }


    /**
     *
     */
    private void setListner () {

        edit_profile.setOnClickListener(submit_ClickListener);
        new_profile.setOnClickListener(new_submit_ClickListener);
        my_iconView.setOnClickListener(iconChg_ClickListener);
        prof_list.setOnClickListener(prof_list_ClickListener);
        test_list.setOnClickListener(test_list_ClickListener);
        maptest.setOnClickListener(maptest_ClickListener);
        jasontest.setOnClickListener(jasontest_ClickListener);

    }

    private void moveToEditAct() {

        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);

    }

    private void moveToNewAct() {

        Intent intent = new Intent(this, NewFriendActivity.class);
        startActivity(intent);

    }


    private void depProfileData () {

        nameView.setText(name);
        if (user_icon != null) {
            Bitmap user_icon_bm= BitmapFactory.decodeByteArray(user_icon, 0, user_icon.length);
            my_iconView.setImageBitmap(user_icon_bm);
        }


        if (sex == 1) {
            sexView.setText("男性");

        } else if (sex == 2) {
            sexView.setText("女性");

        }

        nationView.setText(nation);
        emailView.setText(email);
        addressView.setText(address);


        // 友人リストを表示する
        int c = userData.size();
        v("check_count", c + "");
        if (c > 0) {

            for (int i = 0; i < c; i++) {

                ImageView iconView = new ImageView(this);
                TextView user_nameView = new TextView(this);

                Map<String, Object> mapObject = userData.get(i);

                String nn = (String) mapObject.get("friend_name");
                v("check_friend_name",nn);

                user_nameView.setText(mapObject.get("friend_name") + "");


                LinearLayout friendListBox = new LinearLayout(this);

                friendListBox.setOrientation(LinearLayout.VERTICAL);


                byte[] friend_icon = friendIconData.get(i);
                if (friend_icon != null) {
                    Bitmap friend_icon_bm= BitmapFactory.decodeByteArray(friend_icon, 0, friend_icon.length);
                    iconView.setImageBitmap(friend_icon_bm);
                    v("hello","111");

                } else {
                    Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.user_icon);
                    iconView.setImageBitmap(bm);
                    v("hello", "333");

                }

                friendListBox.addView(iconView, createParam(WC, WC));
                friendListBox.addView(user_nameView,createParam(WC,WC));

                LinearLayout.LayoutParams layoutParams = createParam(WC, WC);
                layoutParams.setMargins(10,0,10,0);
                friend_listlView.addView(friendListBox,layoutParams);
            }
        } else {


            TextView user_nameView = new TextView(this);
            user_nameView.setText("no friend!");
            friend_listlView.addView(user_nameView, createParam(WC, WC));
//            underView.removeView(hs);
//            underView.removeView(fr_ttlView);

        }


    }

    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }



    /**
     * プロフィール編集登録
     */
    private void  submit_Click (View v) {

        v("user_id",user_id+":");
        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("user_id", user_id);

        startActivity(intent);


    }

    /**
     * プロフィール新規登録
     */
    private void  new_submit_Click (View v) {

        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("user_id", 0);

        startActivity(intent);


    }

    /**
     */
    private void  prof_list_Click () {

        Intent intent = new Intent(this, MyListViewActivity.class);
        startActivity(intent);


    }

    private void  maptest_Click () {

        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);


    }

    private void  jasontest_Click () {

        Intent intent = new Intent(this, JASON.class);
        startActivity(intent);


    }


    private void  test_list_Click () {

        Intent intent = new Intent(this, NewFriendActivity.class);
        startActivity(intent);


    }



    /**
     *
     */
    public void setYourProfile () {

        dbObj = dbHelper.getReadableDatabase();

        String sql = "SELECT id,name,icon_blob,sex,nation,email,address FROM profile_table order by id desc";
        Cursor c = dbObj.rawQuery(sql, null);

//        c.moveToFirst();


        if (c.getCount() > 0) {
            int count = 0;
            while (c.moveToNext()) {
                v("hello",""+c.getString(1)+count);

                if (count == 0) {
                    name = c.getString(1);
                    user_id = c.getInt(0);
                    user_icon = c.getBlob(2);
                    sex = c.getInt(3);
                    nation = c.getString(4);
                    email = c.getString(5);
                    address = c.getString(6);

                }
                count++;

                Map<String, Object> map;
                map = new HashMap<String, Object>();
                map.put("id", c.getInt(0));
                map.put("friend_name", c.getString(1)+"");
                userData.add(map);
                friendIconData.add(c.getBlob(2));

            }
        } else {
            v("hello", "no fdaa");
        }
        c.close();
        dbObj.close();


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
