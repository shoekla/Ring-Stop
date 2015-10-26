package com.example.abirshukla.justjava;

import android.app.AlertDialog;
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

public class add3 extends ActionBarActivity {
    String name, title;
    int beginTime, beginHour;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add3);
        Bundle nameAndTime = getIntent().getExtras();
        title = nameAndTime.getString("title");
        TextView te = (TextView) findViewById(R.id.textViewEnd);
        te.setText("Enter End Time For "+title);
        beginTime = nameAndTime.getInt("BeginTime");
        name = nameAndTime.getString("className");
        beginHour = nameAndTime.getInt("hourB");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add3, menu);
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
    public void subTimeEnd (View view) {
        Intent g = new Intent(this, MainActivity.class);
        TimePicker t = (TimePicker) findViewById(R.id.timePicker2);
        int hour = t.getCurrentHour();
        int minute = t.getCurrentMinute();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Time Is not after the begining Time");

        /*
        if (beginHour > endTime){
            alertDialogBuilder.show();
            return;
        }
        */

        if (beginHour < hour) {
            if ((hour) > 12) {
                int hourA = hour - 12;
                if (minute < 10) {
                    name = name + " " + hourA + ": 0"+ minute + " PM)";
                }
                else {
                    name = name + " " + hourA + ": "+ minute + " PM)";
                }
            }
            else {
                if (minute < 10) {
                    name = name + " " + hour + ": 0"+ minute + " AM)";
                }
                else {
                    name = name + " " + hour + ": "+ minute + " AM)";
                }
            }
        }
        int endTime = (hour * 100) + minute;
        g.putExtra("beginTime", beginTime);
        g.putExtra("endTime", endTime);
        g.putExtra("name", name);
        startActivity(g);
    }
}
