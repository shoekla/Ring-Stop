package com.example.abirshukla.RingStop;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.abirshukla.RingStop.R;

public class loc extends ActionBarActivity {
    Button btnShowLoc;
    GPSTracker gps;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);
        t = (TextView) findViewById(R.id.textView1);

        btnShowLoc = (Button) findViewById(R.id.locBut);
        btnShowLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(loc.this);
                if (gps.canGetLocation()) {
                    double latitiude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    t.setText("Location: (Lat: "+latitiude+", Long: "+longitude+").");
                }
                else {
                    gps.showSettingsAlert();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_loc, menu);
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
