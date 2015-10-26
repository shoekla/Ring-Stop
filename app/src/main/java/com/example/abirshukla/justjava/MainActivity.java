package com.example.abirshukla.justjava;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
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


public class MainActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> fetchedList = listTime.getArrNames();
        for (int i = 0; i < fetchedList.size();i++) {
            list.add(fetchedList.get(i));
        }
        final ArrayAdapter<String> cAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView listview;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(cAdapter);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Nothing To Update");
        final AlertDialog.Builder already = new AlertDialog.Builder(this);
        already.setMessage("Class Already Exists");
        Button updateButton = (Button) findViewById(R.id.updateB);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle addedData = getIntent().getExtras();
                if (addedData == null){
                    alertDialogBuilder.show();
                }else {
                    String className = addedData.getString("name");
                    int beginTime = addedData.getInt("beginTime");
                    int endTime = addedData.getInt("endTime");
                    if (!list.contains(className)) {
                        listTime.addToList(className, beginTime, endTime);
                        list.add(className);
                        cAdapter.notifyDataSetChanged();
                    }
                    else {
                        already.show();
                    }
                }
            }
        });


    }
    public void moveToAdd (View view) {
        Intent m = new Intent(this, add.class);

        startActivity(m);
    }
    public void checkStat (View view) {
        Intent q = new Intent(this, checkStatus.class);
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR)*100;
        //int day = c.get(Calendar.DAY_OF_WEEK);
        int minute = c.get(Calendar.MINUTE);
        int time = hour+minute;
        int status = listTime.status(time);
        if (status >= 0) {
            String end = listTime.getEnd(status);
            q.putExtra("message", "Phone is now on vibrate, and will remain on vibrate until " + end);
            AudioManager audiomanage = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            audiomanage.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        }
        else {
            q.putExtra("message", "Phone is still on ringer");
        }

        startActivity(q);
    }



      /*
*/

}
