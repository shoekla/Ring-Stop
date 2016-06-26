package com.example.abirshukla.RingStop;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {
    private final int REQ_CODE_SPEECH_INPUT = 100;
    int currentPhoneMode;
    SharedPreferences sharedPref;
    ArrayAdapter<String> cAdapter;
    ArrayList<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        try {
            if (sharedPref != null) {
                String nameList = sharedPref.getString("names", "");
                String dayList = sharedPref.getString("days", "");
                String begin = sharedPref.getString("beginTimes", "");
                String endT = sharedPref.getString("endTimes", "");
                String modes = sharedPref.getString("modes", "");
                if (nameList.length() > 0) {
                    modes = modes.substring(1, modes.length() - 1);
                    nameList = nameList.substring(1, nameList.length() - 1);
                    dayList = dayList.substring(1, dayList.length() - 1);
                    begin = begin.substring(1, begin.length() - 1);
                    endT = endT.substring(1, endT.length() - 1);
                    String[] days = listTime.deleteDups(dayList.split(","));
                    String[] names = listTime.deleteDups(nameList.split(","));
                    String[] bList = listTime.deleteDups(begin.split(","));
                    int[] beginList = new int[bList.length];
                    String[] eList = listTime.deleteDups(endT.split(","));
                    int[] endList = new int[eList.length];
                    String[] modeList = listTime.deleteDups(modes.split(","));
                    for (int k = 0; k < eList.length; k++) {
                        bList[k] = bList[k].trim();
                        eList[k] = eList[k].trim();
                        beginList[k] = Integer.parseInt(bList[k]);
                        endList[k] = Integer.parseInt(eList[k]);
                    }

                    for (int i = 0; i < names.length; i++) {
                        try {
                            listTime.addToList(names[i] + "(" + beginList[i] + ". " + endList[i] + ")", modeList[i], "Something", beginList[i], endList[i], days[i]);

                        } catch (Exception e) {
                            continue;
                        }
                    }
                }

            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Failed To Load Data", Toast.LENGTH_SHORT).show();
        }
        int first = listTime.getFirst();
        currentPhoneMode = AudioManager.MODE_CURRENT;
        list = new ArrayList<String>();
        ArrayList<String> fetchedList = listTime.getArrNames();
        for (int i = 0; i < fetchedList.size();i++) {
            list.add(fetchedList.get(i));
        }
        list = listTime.deleteDups(list);
        cAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ListView listview;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(cAdapter);
        registerForContextMenu(listview);
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
                    if (!className.contains("TH")) {
                        className= className.replace("H","TH");
                    }
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
                boolean b = checkStat();
                if (b) {
                    Intent intent = new Intent();
                    PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this,0,intent,0);
                    Notification n = new Notification.Builder(MainActivity.this)
                            .setContentTitle("Ring Stop Notification")
                            .setContentText("Ring Stop Changed Your Ringer")
                            .setContentIntent(pendingIntent).getNotification();
                    n.flags = Notification.FLAG_AUTO_CANCEL;
                    NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                    nm.notify(1,n);
                }
                else {
                    AudioManager audiomanage = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                }
            }
        }, 0, 30000);


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.deleteId:
                listTime.deleteNameFromList(info.position);
                list.removeAll(Collections.singleton(list.get(info.position)));
                cAdapter.notifyDataSetChanged();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
        //return super.onContextItemSelected(item);
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
        savedInstanceState.putStringArrayList("days", listTime.getDays());
        savedInstanceState.putStringArrayList("modes", listTime.getModes());
        savedInstanceState.putStringArrayList("endTimeMess", listTime.getEntTimeSes());
        savedInstanceState.putIntegerArrayList("beginTimes", listTime.getBeginTimes());
        savedInstanceState.putIntegerArrayList("endTimes", listTime.getEndTimes());
        super.onSaveInstanceState(savedInstanceState);

    }

    @Override
    protected void onDestroy() {
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        String names = listTime.getArrNames().toString();
        String modes = listTime.getModes().toString();
        String endMess = listTime.getEntTimeSes().toString();
        String endTimes = listTime.getEndTimes().toString();
        String beginTime = listTime.getBeginTimes().toString();
        String days = listTime.getDays().toString();
        editor.putString("modes", modes);
        editor.putString("endMess",endMess);
        editor.putString("endTimes",endTimes);
        editor.putString("beginTimes",beginTime);
        editor.putString("names",names);
        editor.putString("days",days);
        editor.commit();
        super.onDestroy();
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
    public boolean checkStat () {
        Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY)*100;
        int day = c.get(Calendar.DAY_OF_WEEK);
        int minute = c.get(Calendar.MINUTE);
        int time = hour+minute;
        int status = listTime.status(time, day);
        AudioManager audiomanage = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        if (status >= 0) {
            String end = listTime.getEnd(status);
            //q.putExtra("message", "Phone is now on vibrate, and will remain on vibrate until " + end);
            if (listTime.vibrate(status)) {
                    audiomanage.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    return true;
                }
                else {
                audiomanage.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                return true;
                }
                //nm.notify(0,n);
               // q.putExtra("message", "Phone is now on vibrate, and will remain on vibrate until " + end);
            }
        else {
            audiomanage.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                return false;
            //q.putExtra("message", "Phone is still on ringer");
        }

        //startActivity(q);
    }



      /*
*/

}
