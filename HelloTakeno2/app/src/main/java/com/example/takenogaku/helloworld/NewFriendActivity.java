package com.example.takenogaku.helloworld;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.takenogaku.lib.PersonOpenHelper;
import static android.util.Log.v;
public class NewFriendActivity extends ActionBarActivity {
    private SQLiteDatabase dbObj;
    private PersonOpenHelper dbHelper;
    private String name = "";
    private String[] members;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_friend);
        dbHelper = new PersonOpenHelper(this);
        setYourProfile();
        //       depProfileData();
        ListView lv;
//            String[] members = {name};
        lv = (ListView) findViewById(R.id.fr_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_expandable_list_item_1, members);
        lv.setAdapter(adapter);
        System.out.println(name + "fuck!!");
    }
    public void setYourProfile() {
        dbObj = dbHelper.getReadableDatabase();
        String sql = "SELECT name FROM profile_table order by id desc";
        Cursor c = dbObj.rawQuery(sql, null);
        members = new String[c.getCount()];
//        c.moveToFirst();
        System.out.println(name + "fuck!!");
        if (c.getCount() > 0) {
            int count = 0;
            while (c.moveToNext()) {
                v("hello",""+c.getString(0)+count+"fuck!!");
                members[count] = c.getString(0)+"";
                count++;
            }
        } else {
            v("hello", "no fdaa");
        }
        c.close();
        dbObj.close();
//        return name;
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_friend, menu);
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