package com.example.abirshukla.justjava;

/**
 * Created by abirshukla on 10/25/15.
 */
public class timeSlot {
    String name;
    int beginTime, endTime;
    //int[] days;
    public timeSlot(String name, int beginTime, int endTime){
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.name = name;

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
}
