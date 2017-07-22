package com.adam.newyearcountdown;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSetCount = (Button)findViewById(R.id.setCountBtn);
        Button btnStartCount = (Button)findViewById(R.id.countDownBtn);

        btnSetCount.setOnClickListener(this);
        btnStartCount.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.setCountBtn){

        }else{

        }
    }
}
