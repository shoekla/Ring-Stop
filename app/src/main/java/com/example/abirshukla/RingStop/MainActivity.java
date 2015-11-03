package com.example.abirshukla.RingStop;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import android.widget.ArrayAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    private final int REQ_CODE_SPEECH_INPUT = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        int first = listTime.getFirst();
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
        registerForContextMenu(listview);
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Nothing To Update");
        final AlertDialog.Builder already = new AlertDialog.Builder(this);
        /*
        Button voiceB = (Button) findViewById(R.id.voiceButton);
        voiceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
        */
        already.setMessage("Class Already Exists");
            if (first == 0) {
                listTime.addFirst();
            }
            else {
                Bundle addedData = getIntent().getExtras();
                if (addedData == null) {
                } else {
                    String className = addedData.getString("name");
                    int beginTime = addedData.getInt("beginTime");
                    int endTime = addedData.getInt("endTime");
                    String mode = addedData.getString("mode");
                    String endTimeS = addedData.getString("endTimeStr");
                    String days = addedData.getString("days");
                    if (!list.contains(className)) {
                        listTime.addToList(className, mode, endTimeS, beginTime, endTime, days);
                        list.add(className);
                        cAdapter.notifyDataSetChanged();
                    } else {
                        already.show();
                    }
                }
            }

        /*
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView parent, View view, int position, long id) {
                cAdapter.remove(some_data[position]);
                cAdapter.notifyDataSetChanged();
            }
        });
        */
        Timer mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkStat();
            }
        }, 0, 60000);


    }

    private void promptSpeechInput() {
        String speech_prompt = "Enter Information about Event";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                speech_prompt);
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),"Speech Not Supported",
                    Toast.LENGTH_SHORT).show();
            return;
        }
    }
    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent v = new Intent(this, voiceRes.class);
        String res = "";
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    res = result.get(0);
                }
                break;
            }

        }
        v.putExtra("speech", res);
        startActivity(v);
    }

    /*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        final ArrayList<String> list = new ArrayList<String>();
        ArrayList<String> fetchedList = listTime.getArrNames();
        for (int i = 0; i < fetchedList.size();i++) {
            list.add(fetchedList.get(i));
        }
        if (v.getId()==R.id.listView) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
            menu.setHeaderTitle(list.get(info.position));
            String[] menuItems = {"Edit","Delete"};
            for (int i = 0; i<menuItems.length; i++) {
                menu.add(Menu.NONE, i, i, menuItems[i]);
            }

        }
    }
    */
    public void moveToLoc (View view) {
        Intent l = new Intent(this, loc.class);
        startActivity(l);
    }
    @Override
    protected void onResume() {
        super.onResume();
        checkStat();
    }

    @Override
    protected void onPause() {
        Timer mTimer = new Timer();
        mTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkStat();
            }
        }, 0, 60000);
        super.onPause();
    }


    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putStringArrayList("names", listTime.getArrNames());
        savedInstanceState.putStringArrayList("days",listTime.getDays());
        savedInstanceState.putStringArrayList("modes",listTime.getModes());
        savedInstanceState.putStringArrayList("endTimeMess", listTime.getEntTimeSes());
        savedInstanceState.putIntegerArrayList("beginTimes", listTime.getBeginTimes());
        savedInstanceState.putIntegerArrayList("endTimes", listTime.getEndTimes());
        super.onSaveInstanceState(savedInstanceState);

    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ArrayList<String> names = savedInstanceState.getStringArrayList("names");
        ArrayList<String> days = savedInstanceState.getStringArrayList("days");
        ArrayList<String> modes = savedInstanceState.getStringArrayList("modes");
        ArrayList<String> endMes = savedInstanceState.getStringArrayList("endTimeMess");
        ArrayList<Integer> beginTimes = savedInstanceState.getIntegerArrayList("beginTimes");
        ArrayList<Integer> endTimes = savedInstanceState.getIntegerArrayList("endTimes");
        for (int i =0; i < names.size(); i++) {
            listTime.addToList(names.get(i),modes.get(i),endMes.get(i),beginTimes.get(i),endTimes.get(i),days.get(i));
        }
    }

    public void moveToAdd (View view) {
        Intent m = new Intent(this, addmenu.class);
        startActivity(m);
    }
    /*
    public void movetoLoc (View view) {
        Intent l = new Intent(this, loc.class);
        l.putExtra("loc",locationStr);
        startActivity(l);
    }
    */
    public void checkStat () {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY)*100;
        int day = c.get(Calendar.DAY_OF_WEEK);
        int minute = c.get(Calendar.MINUTE);
        int time = hour+minute;
        int status = listTime.status(time, day);
        if (status >= 0) {
            String end = listTime.getEnd(status);
            //q.putExtra("message", "Phone is now on vibrate, and will remain on vibrate until " + end);
            AudioManager audiomanage = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            if (listTime.vibrate(status)) {
                audiomanage.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
               // q.putExtra("message", "Phone is now on vibrate, and will remain on vibrate until " + end);
            }
            else {
                audiomanage.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                //q.putExtra("message", "Phone is now on silent, and will remain on silent until " + end);
            }
        }
        else {
            AudioManager audiomanage = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
            audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            //q.putExtra("message", "Phone is still on ringer");
        }

        //startActivity(q);
    }



      /*
*/

}
