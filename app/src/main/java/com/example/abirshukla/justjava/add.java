package com.example.abirshukla.justjava;

import android.content.Intent;
import android.widget.ListView;
import java.util.ArrayList;
import android.widget.ArrayAdapter;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.widget.EditText;

public class add extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        /*Bundle getTimeUser = getIntent().getExtras();
        int hour, min;
        if (getTimeUser == null) {
            return;
        }
        else {
            hour = getTimeUser.getInt("userHour");
            min = getTimeUser.getInt("userMinute");
            TextView t = (TextView) findViewById(R.id.textView);
            t.setText("Your Time: "+hour+": "+min);
        }
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add, menu);
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
    public void submitString (View view) {
        Intent m = new Intent(this, add2.class);
        final EditText input = (EditText) findViewById(R.id.name);
        String str = input.getText().toString();
        if (str == null) {
            input.setError("Please Enter Text");
            return;
        }
        if (str.length() == 0) {
            input.setError("Please Enter Text");
            return;
        }
        m.putExtra("className", str);

        startActivity(m);
    }
}
