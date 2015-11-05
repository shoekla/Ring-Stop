package com.example.abirshukla.RingStop;

import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.abirshukla.RingStop.R;

import java.util.ArrayList;

public class locas extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locas);
        ArrayList<locationForUsers> locUser = listTime.getLocs();
        Location l = locUser.get(locUser.size()-1).getLocUser();
        String n = locUser.get(locUser.size()-1).getName();
        TextView name = (TextView) findViewById(R.id.name);
        name.setText("Name: "+n);
        TextView lat = (TextView) findViewById(R.id.lat);
        TextView longit = (TextView) findViewById(R.id.longit);
        lat.setText("Latitiude: "+l.getLatitude());
        longit.setText("Longitiude: "+l.getLongitude());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_locas, menu);
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
