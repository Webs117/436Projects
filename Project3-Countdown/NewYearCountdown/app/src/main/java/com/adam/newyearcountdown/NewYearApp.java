package com.adam.newyearcountdown;

import android.app.Application;
import android.util.Log;

/**
 * Created by Adam on 7/29/2017.
 */

public class NewYearApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Log.d("New Year Countdown", "App started");
    }
}
