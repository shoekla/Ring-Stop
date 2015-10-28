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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_res);
        Bundle voiceIn = getIntent().getExtras();
        String text = voiceIn.getString("speech");
        text = text.toLowerCase();
        TextView v = (TextView) findViewById(R.id.voice);
        TextView t = (TextView) findViewById(R.id.tV);
        String words[] = text.split(" ");

        int count = 0;
        for (int i =0; i < words.length; i++) {
            if (words[i].equals("vibrate")) {
                mode = "Vibrate";
            }
            if (words[i].equals("silent")) {
                mode = "Silent";
            }
            if (words[i].contains(":")) {
                if (count == 0) {
                    hour = words[i].substring(0, words[i].indexOf(":"));
                    min = words[i].substring(words[i].indexOf(":") + 1);
                    int h = Integer.parseInt(hour);
                    int m = Integer.parseInt(min);
                    beginTime = (h * 100) + m;
                    beginMess = words[i];
                    count++;
                }
                else {
                    hour = words[i].substring(0, words[i].indexOf(":"));
                    min = words[i].substring(words[i].indexOf(":") + 1);
                    int h = Integer.parseInt(hour);
                    int m = Integer.parseInt(min);
                    endTime= (h * 100) + m;
                    endMess = words[i];
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
                days  = days +"TH";
            }
            if (words[i].equals("friday")) {
                days = days + "F";
            }
            if (words[i].equals("saturday")) {
                days = days + "SA";
            }
            if (words[i].equals("sunday")) {
                days = days + "SU";
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

        v.setText("Begin Time: " + beginMess +", End Time: "+ endMess+", Mode: "+mode +" Days: "+days+"!");
        t.setText(text);
        Button s = (Button) findViewById(R.id.sub);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                promptSpeechInput();
            }
        });
    }
    private void promptSpeechInput() {
        String speech_prompt = "What do you want";
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
        String listOfDays = "";
        if (days.length() > 1) {
            for (int i = 0; i < days.length(); i++) {
                if (i == 0) {
                    listOfDays = " (" + days.charAt(i);
                } else if (i != days.length() - 1) {
                    listOfDays = listOfDays + "," + days.charAt(i);
                } else {
                    listOfDays = listOfDays + "," + days.charAt(i) + ")";
                }
            }
            res = res + r;
        }
        else if (days.length() == 0) {
            res = res + " (NO DAYS)";
        }
        else if (days.length() == 1) {
            res = res + " ("+days+")";
        }
        res = res + " ("+ mode +") ";
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
