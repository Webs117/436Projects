package com.adam.newyearcountdown;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.app.PendingIntent;
import android.content.Intent;



import org.w3c.dom.Text;

public class MainActivity extends Activity implements View.OnClickListener {

        private ArrayAdapter<CharSequence> adapter;
        private Spinner spinner;
        private String countDownTime;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSetCount = (Button)findViewById(R.id.setCountBtn);
        Button btnStartCount = (Button)findViewById(R.id.countDownBtn);

        btnSetCount.setOnClickListener(this);
        btnStartCount.setOnClickListener(this);

        spinner = (Spinner) findViewById(R.id.notifSpinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.count_times, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setEnabled(false);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.setCountBtn){
            EditText timeInput = (EditText) findViewById(R.id.inputTime);
            String time = timeInput.getText().toString();

            int timeNum = Integer.parseInt(time);

            if(timeNum > 5 && timeNum <= 120 && timeNum % 5 == 0){
                countDownTime = time;
                spinner.setEnabled(true);
            }else{
                spinner.setEnabled(true);
            }
        }else if(v.getId() == R.id.countDownBtn){
            String spinnerItem = spinner.getSelectedItem().toString();
            Toast.makeText(getApplicationContext(), "Spinner value: " + spinnerItem, Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(), "Countdown has been started.", Toast.LENGTH_LONG).show();
            //new CountdownTask(getApplicationContext()).execute(countDownTime, spinnerItem);


            Notification.Builder mBuilder =
                    new Notification.Builder(this)
                            .setSmallIcon(R.mipmap.ic_launcher)
                            .setContentTitle("My notification")
                            .setContentText("Hello World!");

            Intent resultIntent = new Intent(this, MainActivity.class);
            // Because clicking the notification opens a new ("special") activity, there's
            // no need to create an artificial back stack.
            PendingIntent resultPendingIntent =
                    PendingIntent.getActivity(
                            this,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT
                    );

            // Sets an ID for the notification
            int mNotificationId = 001;
            // Gets an instance of the NotificationManager service
            NotificationManager mNotifyMgr =
                    (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            //add pending intent
            mBuilder.setContentIntent(resultPendingIntent);

            // Builds the notification and issues it.
            mNotifyMgr.notify(mNotificationId, mBuilder.build());


        }

    }

}
