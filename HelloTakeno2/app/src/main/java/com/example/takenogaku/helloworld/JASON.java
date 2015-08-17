package com.example.takenogaku.helloworld;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.util.Log.v;


public class JASON extends ActionBarActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jason);
        textView = (TextView) findViewById(R.id.j_add);


        String responce_json = "{\"name\":\"takeno\"," +
                "\"email\":\"ggggg@gmail.com\"," +
                "\"sex\":1," +
                "\"age\":20," +
                "\"address\":\"tokyo japan\"," +
                "\"nationality\":\"JAPANESE\"," +
                "\"hobby\":[\"baseball\",\"music\",\"trip\"]}";

        try {
            JSONObject jsonObj = new JSONObject(responce_json);
            JSONArray jsonHobbyArg = jsonObj.getJSONArray("hobby");
            String hobby1 = jsonHobbyArg.getString(0);
            String hobby2 = jsonHobbyArg.getString(1);
            String name = jsonObj .getString("name");
            String email = jsonObj .getString("email");
            int sex= jsonObj .getInt("sex");
            int age= jsonObj .getInt("age");


            String address = jsonObj.getString("address");

            textView.setText(address);

            v("hello_app", address + "::::");
        //    return;

        }
        catch (JSONException e) {
            // TODO é©ìÆê∂ê¨Ç≥ÇÍÇΩ
            e.printStackTrace();
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_jason, menu);
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
