package com.example.abirshukla.RingStop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.EditText;

import java.util.ArrayList;

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
        Spinner n = (Spinner) findViewById(R.id.phoneMode);
        String mode = String.valueOf(n.getSelectedItem());
        m.putExtra("mode", mode);
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
        CheckBox mon = (CheckBox) findViewById(R.id.monCheck);
        CheckBox tues = (CheckBox) findViewById(R.id.tuesCheck);
        CheckBox wen = (CheckBox) findViewById(R.id.wenCheck);
        CheckBox thur = (CheckBox) findViewById(R.id.thurCheck);
        CheckBox fri = (CheckBox) findViewById(R.id.friCheck);
        CheckBox sat = (CheckBox) findViewById(R.id.satCheck);
        CheckBox sun = (CheckBox) findViewById(R.id.sunCheck);
        ArrayList<String> dayList = new ArrayList<String>();
        if (mon.isChecked()) {
            dayList.add("M");
        }
        if (tues.isChecked()) {
            dayList.add("T");
        }
        if (wen.isChecked()) {
            dayList.add("W");
        }
        if (thur.isChecked()) {
            dayList.add("TH");
        }
        if (fri.isChecked()) {
            dayList.add("F");
        }
        if (sat.isChecked()) {
            dayList.add("SA");
        }
        if (sun.isChecked()) {
            dayList.add("SU");
        }
        m.putExtra("days",dayList);

        startActivity(m);
    }
}
