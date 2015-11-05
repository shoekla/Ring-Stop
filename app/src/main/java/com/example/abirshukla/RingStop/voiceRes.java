package com.example.abirshukla.RingStop;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abirshukla.RingStop.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class voiceRes extends ActionBarActivity {
    private final int REQ_CODE_SPEECH_INPUT = 100;
    String mode = "";
    String endMess = "";
    int beginTime = 0;
    String hour = "";
    String min = "";
    int endTime = 0;
    String days = "";
    String beginMess = "";
    String dayMess = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_res);
        Bundle voiceIn = getIntent().getExtras();
        String text = voiceIn.getString("speech");
        dayMess = "";
        text = text.toLowerCase();
        String words[] = text.split(" ");

        int count = 0;
        if (listTime.locInLocs(text)) {
            Intent m = new Intent(this, MainActivity.class);
            startActivity(m);
        }
        for (int i =0; i < words.length; i++) {
            if (words[i].equals("vibrate")) {
                mode = "Vibrate";
            }
            if (words[i].equals("silent")) {
                mode = "Silent";
            }
            if (words[i].equals("1") || words[i].equals("2") || words[i].equals("3") || words[i].equals("4") || words[i].equals("5") || words[i].equals("6") || words[i].equals("7") || words[i].equals("8") || words[i].equals("9") || words[i].equals("10") || words[i].equals("11") || words[i].equals("12")) {
                if (beginMess.length() <= 1) {
                    if (i != words.length-1) {
                        if (words[i+1].equals("a.m.")) {
                            hour = words[i];
                            int h = Integer.parseInt(hour);
                            beginTime = h * 100;
                            beginMess = h+": "+"00 AM";
                        }
                        else {
                            hour = words[i];
                            int h = Integer.parseInt(hour);
                            beginTime = h * 100;
                            if (h - 12 > 0) {
                                beginMess = (h - 12) + ": " + "00 PM";
                            }
                            else {
                                beginMess = (h) + ": " + "00 PM";
                            }

                        }
                    }
                    else {

                        hour = words[i];
                        int h = Integer.parseInt(hour);
                        beginTime = h * 100;
                        if (h - 12 > 0) {
                            beginMess = (h - 12) + ": " + "00 PM";
                        }
                        else {
                            beginMess = (h) + ": " + "00 PM";
                        }
                    }
                }
                else {
                    if (i != words.length-1) {
                        if (words[i+1].equals("a.m.")) {
                            hour = words[i];
                            int h = Integer.parseInt(hour);
                            endTime = h * 100;
                            endMess = h+": "+"00 AM";
                        }
                        else {
                            hour = words[i];
                            int h = Integer.parseInt(hour);
                            endTime = h * 100;
                            if (h - 12 > 0) {
                                endMess = (h - 12) + ": " + "00 PM";
                            }
                            else {
                                endMess = (h) + ": " + "00 PM";
                            }
                        }
                    }
                    else {

                        hour = words[i];
                        int h = Integer.parseInt(hour);
                        endTime = h * 100;
                        if (h - 12 > 0) {
                            endMess = (h - 12) + ": " + "00 PM";
                        }
                        else {
                            endMess = (h) + ": " + "00 PM";
                        }
                    }
                }
            }
            if (words[i].contains(":")) {
                if (beginMess.length() <= 1) {
                    if (i != words.length - 1) {
                        if (words[i + 1].equals("a.m.")) {
                            hour = words[i].substring(0, words[i].indexOf(":"));
                            min = words[i].substring(words[i].indexOf(":") + 1);
                            int h = Integer.parseInt(hour);
                            int m = Integer.parseInt(min);
                            beginTime = (h * 100) + m;
                            beginMess = words[i] + "AM";
                            count++;
                        } else {
                            hour = words[i].substring(0, words[i].indexOf(":"));
                            min = words[i].substring(words[i].indexOf(":") + 1);
                            int h = Integer.parseInt(hour);
                            h = h + 12;
                            int m = Integer.parseInt(min);
                            beginTime = (h * 100) + m;
                            beginMess = words[i] + "PM";
                            count++;

                        }
                    } else {
                        hour = words[i].substring(0, words[i].indexOf(":"));
                        min = words[i].substring(words[i].indexOf(":") + 1);
                        int h = Integer.parseInt(hour);
                        h = h + 12;
                        int m = Integer.parseInt(min);
                        beginTime = (h * 100) + m;
                        beginMess = words[i] + "PM";
                        count++;
                    }
                }
                else {
                    if (i != words.length - 1) {
                        if (words[i + 1].equals("a.m.")) {
                            hour = words[i].substring(0, words[i].indexOf(":"));
                            min = words[i].substring(words[i].indexOf(":") + 1);
                            int h = Integer.parseInt(hour);
                            int m = Integer.parseInt(min);
                            endTime = (h * 100) + m;
                            endMess = words[i] + "AM";
                            count++;
                        } else {
                            hour = words[i].substring(0, words[i].indexOf(":"));
                            min = words[i].substring(words[i].indexOf(":") + 1);
                            int h = Integer.parseInt(hour);
                            h = h + 12;
                            int m = Integer.parseInt(min);
                            endTime = (h * 100) + m;
                            endMess = words[i] + "PM";
                            count++;

                        }
                    } else {
                        hour = words[i].substring(0, words[i].indexOf(":"));
                        min = words[i].substring(words[i].indexOf(":") + 1);
                        int h = Integer.parseInt(hour);
                        h = h + 12;
                        int m = Integer.parseInt(min);
                        endTime = (h * 100) + m;
                        endMess = words[i] + "PM";
                        count++;
                    }
                }

            }
            if (words[i].equals("monday")) {
                days  = days +"M";
            }

            if (words[i].equals("tuesday")) {
                days  = days +"T";
            }

            if (words[i].equals("wednesday")) {
                days  = days +"W";
            }
            if (words[i].equals("thursday")) {
                days  = days +"H";
            }
            if (words[i].equals("friday")) {
                days = days + "F";
            }
            if (words[i].equals("saturday")) {
                days = days + "S";
            }
            if (words[i].equals("sunday")) {
                days = days + "U";
            }
            if (words[i].equals("today")) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_WEEK);
                if (day == 1) {
                    days= days+"U";
                }
                else if (day == 2) {
                    days= days+"M";
                }
                else if (day == 3) {
                    days= days+"T";
                }
                else if (day == 4) {
                    days= days+"W";
                }
                else if (day == 5) {
                    days= days+"H";
                }
                else if (day == 6) {
                    days= days+"F";
                }
                else if (day == 7) {
                    days= days+"S";
                }
            }
            if (words[i].equals("tomorrow")) {
                Calendar c = Calendar.getInstance();
                int day = c.get(Calendar.DAY_OF_WEEK);
                if (day == 7) {
                    day = 1;
                }
                else{
                    day = day + 1;
                }
                if (day == 1) {
                    days= days+"U";
                }
                else if (day == 2) {
                    days= days+"M";
                }
                else if (day == 3) {
                    days= days+"T";
                }
                else if (day == 4) {
                    days= days+"W";
                }
                else if (day == 5) {
                    days= days+"H";
                }
                else if (day == 6) {
                    days= days+"F";
                }
                else if (day == 7) {
                    days= days+"S";
                }
            }
        }
        if (days.length() == 0) {
            days = "MTWHFSU";
        }
        String listOfDays = "";
        for (int i = 0; i < days.length(); i++) {
            if (i == 0) {
                if (i+1 != days.length()) {
                    if (days.charAt(i) == 'U') {
                        listOfDays = "(SU";
                    } else if (days.charAt(i) == 'H') {
                        listOfDays = "(TH";
                    } else if (days.charAt(i) == 'S') {
                        listOfDays = "(SA";
                    } else {
                        listOfDays = " (" + days.charAt(i);
                    }
                }
                else {
                    if (days.charAt(i) == 'U') {
                        listOfDays = "(SU)";
                    } else if (days.charAt(i) == 'H') {
                        listOfDays = "(TH)";
                    } else if (days.charAt(i) == 'S') {
                        listOfDays = "(SA)";
                    } else {
                        listOfDays = "(" + days.charAt(i)+")";
                    }

                }
            }
            else if (i != days.length()-1){
                if (days.charAt(i) == 'U'){
                    listOfDays = listOfDays +",SU";
                } else if (days.charAt(i) == 'H') {
                    listOfDays = listOfDays +",TH";
                }
                else if (days.charAt(i) == 'S') {
                    listOfDays = listOfDays +",SA";
                }
                else {
                    if (days.charAt(i) == 'U'){
                        listOfDays = listOfDays +",SU)";
                    } else if (days.charAt(i) == 'H') {
                        listOfDays = listOfDays +",TH)";
                    }
                    else if (days.charAt(i) == 'S') {
                        listOfDays = listOfDays +",SA)";
                    }
                    else {
                        listOfDays = listOfDays + "," + days.charAt(i) + ")";
                    }
                }
            }
            else {
                listOfDays = listOfDays +","+days.charAt(i)+")";
            }
        }

        if (endTime < beginTime) {
            int tim = beginTime;
            String emp = beginMess;
            beginMess = endMess;
            beginTime = endTime;
            endTime = tim;
            endMess = emp;
        }
        if (mode.length() == 0)
            mode = "Vibrate";
        dayMess = listOfDays;
        TextView tv = (TextView) findViewById(R.id.tV);
        TextView b = (TextView) findViewById(R.id.beg);
        TextView e = (TextView) findViewById(R.id.end);
        TextView d = (TextView) findViewById(R.id.day);
        TextView m = (TextView) findViewById(R.id.voice);
        tv.setText("Your Text: "+text);
        m.setText("Mode: "+mode);
        b.setText("Begin Time: "+beginMess);
        e.setText("End Time: "+endMess);
        d.setText("Days: "+dayMess);
        Button s = (Button) findViewById(R.id.sub);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }
    private void promptSpeechInput() {
        String speech_prompt = "What is the name of the event?";
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                speech_prompt);
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Speech Not Supported",
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent v = new Intent(this, MainActivity.class);
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
        String r = "";

        res = res +" ("+beginMess+" - "+endMess+")";
        res = res + " ("+ mode +") ";
        res = res + " "+dayMess;
        v.putExtra("endTimeStr", endMess);
        v.putExtra("beginTime",beginTime);
        v.putExtra("endTime", endTime);
        v.putExtra("mode", mode);
        v.putExtra("days", days);
        v.putExtra("name", res);
        startActivity(v);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_voice_res, menu);
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
