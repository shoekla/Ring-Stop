package com.example.abirshukla.RingStop;

import java.util.ArrayList;

/**
 * Created by abirshukla on 10/25/15.
 */
public class timeSlot {
    public static int len = 0;
    String name, mode, endTimeS;
    int beginTime, endTime;
    String days;
    public timeSlot(String name, String mode, String endTimeS, int beginTime, int endTime, String days){
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.name = name;
        this.mode = mode;
        this.endTimeS = endTimeS;
        this.days = days;
        len++;
    }
    public static int getLen() { return len; };
    public String getName() {
        return this.name;
    }
    public int getBeginTime () {
        return this.beginTime;
    }
    public int getEndTime () {
        return this.endTime;
    }
    public String getMode () {
        return this.mode;
    }
    public String getEndTimeS () {
        return this.endTimeS;
    }
    public String getDays () {
        return this.days;
    }
}
