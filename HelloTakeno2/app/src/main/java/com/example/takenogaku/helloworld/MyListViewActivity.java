package com.example.takenogaku.helloworld;import android.os.Bundle;import android.support.v7.app.ActionBarActivity;import android.view.Menu;import android.view.MenuItem;import android.widget.ListView;import com.example.takenogaku.com.example.object.CustomData;import com.example.takenogaku.lib.CustomAdapter;import java.util.ArrayList;import java.util.List;public class MyListViewActivity extends ActionBarActivity {    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_my_list_view);        showCustomListView ();    }    private void showCustomListView() {        // データの作成        List<CustomData> objects = new ArrayList<CustomData>();        for (int i = 0; i < 10; i++) {            CustomData item1 = new CustomData();            item1.setNameString("takeno" + i);            item1.setAddressString("東京都新宿区" + i);            item1.setCurrentLoop(i);            objects.add(item1);        }        CustomAdapter customAdapater = new CustomAdapter(this, 0, objects,getResources());        ListView listView = (ListView)findViewById(R.id.mylist);        listView.setAdapter(customAdapater);    }    @Override    public boolean onCreateOptionsMenu(Menu menu) {        // Inflate the menu; this adds items to the action bar if it is present.        getMenuInflater().inflate(R.menu.menu_my_list_view, menu);        return true;    }    @Override    public boolean onOptionsItemSelected(MenuItem item) {        // Handle action bar item clicks here. The action bar will        // automatically handle clicks on the Home/Up button, so long        // as you specify a parent activity in AndroidManifest.xml.        int id = item.getItemId();        //noinspection SimplifiableIfStatement        if (id == R.id.action_settings) {            return true;        }        return super.onOptionsItemSelected(item);    }}