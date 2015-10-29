package com.example.abirshukla.RingStop;

import android.app.AlertDialog;
import android.content.Intent;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;

public class add3 extends ActionBarActivity {
    String name, title;
    int beginTime, beginHour;
    String backUpName,mode;
    String days;
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
        backUpName = name;
        mode = nameAndTime.getString("mode");
        days = nameAndTime.getString("days");
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
        String endTimeS;
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Time Is not after the begining Time");

        /*
        if (beginHour > endTime){
            alertDialogBuilder.show();
            return;
        }
        */

            if ((hour) > 12) {
                int hourA = hour - 12;
                if (minute < 10) {
                    name = name + " " + hourA + ": 0"+ minute + " PM) ("+mode+")";
                    endTimeS = hourA+": 0"+minute+" PM.";
                }
                else {
                    name = name + " " + hourA + ": "+ minute + " PM) ("+mode+")";
                    endTimeS = hourA+": "+minute+" PM.";
                }

            }
            else {
                if (minute < 10) {
                    name = name + " " + hour + ": 0"+ minute + " AM) ("+mode+")";
                    endTimeS = hour+": 0"+minute+" AM.";
                }
                else {
                    name = name + " " + hour + ": "+ minute + " AM) ("+mode+")";
                    endTimeS = hour+": "+minute+" AM.";
                }
            }
        String finalName = "";
        String listOfDays = "";
        int endTime = (hour * 100) + minute;
        if (endTime <= beginTime){
            name = backUpName;
            alertDialogBuilder.show();
            return;
        }
        if (days.length() == 0) {
            finalName = name + " (NO DAYS)";
        }
        else if (days.length() == 1) {
            finalName = name + " ("+days.charAt(0)+")";
        }
        else {
            for (int i = 0; i < days.length(); i++) {
                if (i == 0) {
                    listOfDays = " ("+days.charAt(i);
                }
                else if (i != days.length()-1){
                    if (days.charAt(i) == 'U'){
                        listOfDays = listOfDays +", SU";
                    } else if (days.charAt(i) == 'H') {
                        listOfDays = listOfDays +", TH";
                    }
                    else if (days.charAt(i) == 'S') {
                        listOfDays = listOfDays +", SA";
                    }
                    else {
                        listOfDays = listOfDays + "," + days.charAt(i);
                    }
                }
                else {
                    listOfDays = listOfDays +","+days.charAt(i)+")";
                }
            }
            finalName = name + listOfDays;
        }
        g.putExtra("endTimeStr", endTimeS);
        g.putExtra("mode",mode);
        g.putExtra("beginTime", beginTime);
        g.putExtra("endTime", endTime);
        g.putExtra("name", finalName);
        g.putExtra("days", days);
        startActivity(g);
    }
}
