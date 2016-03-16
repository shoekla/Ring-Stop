package com.example.abirshukla.RingStop;


import android.location.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by abirshukla on 10/24/15.
 */
public class listTime {
    public listTime () {

    }
    public static ArrayList<locationForUsers> locs = new ArrayList<locationForUsers>();
    public static ArrayList<locationForUsers> locsForUsers = new ArrayList<locationForUsers>();
    public static ArrayList<String> names = new ArrayList<String>();
    public static int startCount = 0;
    public static int first = 0;
    public static ArrayList<timeSlot> timeSlots = new ArrayList<timeSlot>();
    public static void addFirst () {
        first++;
    }
    public static int getFirst () {
        return first;
    }
    public static int getStartCount () {
        return startCount;
    }
    public static void deleteNameFromList(int index) {
        String item = names.get(index);

        ArrayList<String> temp = new ArrayList<String>();
        for (int i = 0; i < names.size();i++) {
            temp.add(names.get(i));
        }
        names.clear();
        for (int i =0; i < temp.size(); i++) {
            if (!(temp.get(i).equals(item))) {
                names.add(temp.get(i));
            }
        }
        ArrayList<timeSlot> arrT = new ArrayList<>();
        for (int i = 0; i < timeSlot.getLen();i++) {
            arrT.add(timeSlots.get(i));
        }
        timeSlots.clear();
        for (int i = 0; i < timeSlot.getLen();i++) {
            if (i != index) {
                timeSlots.add(arrT.get(i));
            }
        }
        names = listTime.deleteDups(names);

    }
    public static String[] deleteDups(String[] arr) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if(!isInArr(result,arr[i])) {
                result.add(arr[i]);
            }
        }
        String[] resArr = new String[result.size()];
        for (int i = 0; i < result.size();i++) {
            resArr[i] = result.get(i);
        }
        return resArr;
    }
    public static ArrayList<String> deleteDups(ArrayList<String> arr) {
        ArrayList<String> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            if(!isInArr(result,arr.get(i))) {
                result.add(arr.get(i));
            }
        }
        return result;
    }
    public static boolean isInArr (ArrayList<String> arr, String ele) {
        for (int i =0; i < arr.size(); i++) {
            if (arr.get(i).equals(ele)) {
                return true;
            }
        }
        return false;
    }
    public static void setStartCount () {
        startCount = 1;
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
        names = deleteDups(names);
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
    public static ArrayList<locationForUsers> getLocs () {
        return locs;
    }
    public static void addLoc (String name, Location loc) {
        locationForUsers a = new locationForUsers(name, loc);
        locs.add(a);

    }
    public static boolean locInLocs (String text) {
        text = text.toLowerCase();
        text = text.replace(" ","");
        for (int i = 0; i < locs.size(); i++) {
            if (text.contains(locs.get(i).getName().toLowerCase())) {
                addLocUser(locs.get(i).getName());
                return true;
            }
        }
        return false;
    }
    public static void addLocUser (String name) {
        for (int i = 0; i < locs.size(); i++) {
            if (locs.get(i).getName().equals(name)) {
                locsForUsers.add(locs.get(i));
                names.add("(Location Based) "+name);
            }
        }
    }
    public static boolean userLocCheck (double lat, double longit) {
        for (int i = 0; i < locsForUsers.size(); i++) {
            if (locsForUsers.get(i).inLoc(lat,longit)) {
                return true;
            }
        }
        return false;
    }

}
