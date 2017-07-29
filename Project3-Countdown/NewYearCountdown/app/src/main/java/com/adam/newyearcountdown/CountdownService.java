package com.adam.newyearcountdown;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;


public class CountdownService extends Service {

    private NewYearApp app;
    private Timer timer;

    public CountdownService() {
    }

    @Override
    public void onCreate() {
        Log.d("News reader", "Service created");
        app = (NewYearApp) getApplication();

        //startTimer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("News reader", "Service started");
        return START_STICKY;
    }


    @Override
    public IBinder onBind(Intent intent) {
        Log.d("News reader","Service bound - not used!");
        return null;

    }

    @Override
    public void onDestroy() {
        Log.d("News reader", "Service destroyed");
        //stopTimer();
    }

}
