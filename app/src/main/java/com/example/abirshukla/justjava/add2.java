package com.example.abirshukla.justjava;

import android.content.Intent;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Calendar;

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
import android.widget.TimePicker;


public class add2 extends ActionBarActivity {
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add2);
        Bundle nameU = getIntent().getExtras();
        name = nameU.getString("className");
        TextView t = (TextView) findViewById(R.id.textViewNameB);
        t.setText("Enter Start Time For "+name+".");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add2, menu);
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
    public void subTimeOne (View view) {
        Intent f = new Intent(this, add3.class);

        TimePicker t = (TimePicker) findViewById(R.id.timePickerB);
        int hour = t.getCurrentHour();
        int minute = t.getCurrentMinute();
        int time;
        String title = name;
        f.putExtra("hourB", hour);
        f.putExtra("title",title);
            if ((hour) > 12) {
                int hourA = hour - 12;
                if (minute < 10) {
                    name = name + " (" + hourA + ": 0"+ minute + " PM - ";
                }
                else {
                    name = name + " (" + hourA + ": "+ minute + " PM - ";
                }
            } else {
                if (minute < 10) {
                    name = name + " (" + hour + ": 0"+ minute + " AM - ";
                }
                else {
                    name = name + " (" + hour + ": "+ minute + " AM - ";
                }
            }
        time = (hour * 100) + minute;
        f.putExtra("className", name);
        f.putExtra("BeginTime",time);
        startActivity(f);

    }
}
