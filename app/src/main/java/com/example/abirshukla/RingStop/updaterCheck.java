package com.example.abirshukla.RingStop;

/**
 * Created by abirshukla on 10/27/15.
 */
public class updaterCheck {
    boolean firstLaunch;
    public updaterCheck () {
        this.firstLaunch = true;
    }
    public void launchChange() {
        this.firstLaunch = false;
    }
    public boolean getLaunch () {
        return this.firstLaunch;
    }
}
