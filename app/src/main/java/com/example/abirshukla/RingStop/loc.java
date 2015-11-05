package com.example.abirshukla.RingStop;

import android.app.AlertDialog;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.abirshukla.RingStop.R;

public class loc extends ActionBarActivity {
    Button btnShowLoc, btnSubLoc;
    GPSTracker gps;
    TextView t;
    Location lo;
    EditText w;
    int c = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loc);
        t = (TextView) findViewById(R.id.textView1);
        w = (EditText) findViewById(R.id.editText);
        btnSubLoc = (Button) findViewById(R.id.subLoc);
        btnShowLoc = (Button) findViewById(R.id.locBut);
        btnShowLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(loc.this);
                if (gps.canGetLocation()) {
                    lo = gps.getLocation();
                    double latitiude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    t.setText("Location: (Lat: "+latitiude+", Long: "+longitude+"). C: "+c);
                    c++;
                }
                else {
                    gps.showSettingsAlert();
                }
            }
        });

    }
    public void subLoc (View view) {
        Intent l = new Intent(this,locas.class);
        String text= w.getText().toString();
        if (text == null) {
            w.setError("Please Enter Text");
            return;
        }
        if (text.length() == 0) {
            w.setError("Please Enter Text");
            return;
        }
        listTime.addLoc(text, gps.getLocation());
        startActivity(l);
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
