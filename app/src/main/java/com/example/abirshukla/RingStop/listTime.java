package com.example.abirshukla.RingStop;


import java.util.ArrayList;
/**
 * Created by abirshukla on 10/24/15.
 */
public class listTime {
    public listTime () {

    }
    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<timeSlot> timeSlots = new ArrayList<timeSlot>();
    public static void addToList(String name, String mode, String endTimeS, int beginTime, int endTime) {
        names.add(name);
        timeSlot t = new timeSlot(name, mode, endTimeS, beginTime, endTime);
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
        String end = timeSlots.get(i).getEndTimeS();
        return end;
    }
    public static boolean vibrate (int i) {
        String mode = timeSlots.get(i).getMode();
        return mode.equals("Vibrate");
    }

}
