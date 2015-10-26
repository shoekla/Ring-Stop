package com.example.abirshukla.RingStop;

/**
 * Created by abirshukla on 10/25/15.
 */
public class timeSlot {
    String name, mode, endTimeS;
    int beginTime, endTime;
    //int[] days;
    public timeSlot(String name, String mode, String endTimeS, int beginTime, int endTime){
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.name = name;
        this.mode = mode;
        this.endTimeS = endTimeS;

    }
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
}
