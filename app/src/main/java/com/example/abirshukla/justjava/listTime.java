package com.example.abirshukla.justjava;


import java.util.ArrayList;
/**
 * Created by abirshukla on 10/24/15.
 */
public class listTime {
    public listTime () {

    }
    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<timeSlot> timeSlots = new ArrayList<timeSlot>();
    public static void addToList(String name, int beginTime, int endTime) {
        names.add(name);
        timeSlot t = new timeSlot(name, beginTime, endTime);
        timeSlots.add(t);
    }
    public static ArrayList<String> getArrNames() {
        return names;
    }
    public static int status (int time) {
        for (int i = 0; i < timeSlots.size(); i++) {
            if (timeSlots.get(i).getBeginTime() <= time && timeSlots.get(i).getEndTime() >= time)
                return i;
        }
        return -1;
    }
    public static String getEnd (int i) {
        int end = timeSlots.get(i).getEndTime();
        int hour = (end / 100);
        int minute = end = (hour * 100);
        String res = ""+hour +": " + minute+".";
        return res;
    }

}
