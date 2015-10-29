package com.example.abirshukla.RingStop;


import java.util.ArrayList;
/**
 * Created by abirshukla on 10/24/15.
 */
public class listTime {
    public listTime () {

    }
    public static ArrayList<String> names = new ArrayList<String>();
    public static int first = 0;
    public static ArrayList<timeSlot> timeSlots = new ArrayList<timeSlot>();
    public static void addFirst () {
        first++;
    }
    public static int getFirst () {
        return first;
    }
    public static ArrayList<Integer> getBeginTimes () {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < timeSlots.size(); i++) {
            res.add(timeSlots.get(i).getBeginTime());
        }
        return res;
    }
    public static ArrayList<Integer> getEndTimes () {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 0; i < timeSlots.size(); i++) {
            res.add(timeSlots.get(i).getEndTime());
        }
        return res;
    }
    public static ArrayList<String> getModes () {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < timeSlots.size(); i++) {
            res.add(timeSlots.get(i).getMode());
        }
        return res;
    }

    public static ArrayList<String> getEntTimeSes () {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < timeSlots.size(); i++) {
            res.add(timeSlots.get(i).getEndTimeS());
        }
        return res;
    }

    public static ArrayList<String> getDays () {
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < timeSlots.size(); i++) {
            res.add(timeSlots.get(i).getDays());
        }
        return res;
    }
    public static void addToList(String name, String mode, String endTimeS, int beginTime, int endTime, String days) {
        names.add(name);
        timeSlot t = new timeSlot(name, mode, endTimeS, beginTime, endTime, days);
        timeSlots.add(t);
    }

    public static ArrayList<String> getArrNames() {
        return names;
    }
    public static int status (int time, int day) {
        String dayName = "";
        if (day == 1) {
            dayName="U";
        }
        else if (day == 2) {
            dayName="M";
        }
        else if (day == 3) {
            dayName="T";
        }
        else if (day == 4) {
            dayName="W";
        }
        else if (day == 5) {
            dayName="H";
        }
        else if (day == 6) {
            dayName="F";
        }
        else if (day == 7) {
            dayName="S";
        }
        int index = -1;
        for (int i = 0; i < timeSlots.size(); i++) {
            if (timeSlots.get(i).getBeginTime() <= time && timeSlots.get(i).getEndTime() >= time) {
                index = i;
            }
        }
        if (index == -1) {
            return -1;
        }
        String dayList = timeSlots.get(index).getDays();
        for (int i = 0; i < dayList.length(); i++) {
            if (charToString(dayList.charAt(i)).equals(dayName)) {
                return index;
            }
        }
        return -1;

    }
    public static String charToString (char c) {
        String res="";
        res = res+c;
        return res;
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
