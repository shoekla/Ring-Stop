package com.example.abirshukla.RingStop;

import android.location.Location;

/**
 * Created by abirshukla on 10/30/15.
 */
public class locationForUsers {
    public String name;
    public Location locChoosed;
    public locationForUsers (String name, Location locChoosed) {
        this.name = name;
        this.locChoosed = locChoosed;
    }
    public Location getLocUser () {
        return this.locChoosed;
    }
    public String getName () {
        return this.name;
    }

}
