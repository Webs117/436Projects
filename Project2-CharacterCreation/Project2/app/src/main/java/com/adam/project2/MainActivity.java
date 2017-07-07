package com.adam.project2;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

//Small device plan:
//Second activity for stats screen
//Use fragment to load preferences
public class MainActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnWarrior = (Button)findViewById(R.id.warriorBtn);
        Button btnMage = (Button)findViewById(R.id.mageBtn);
        Button btnHealer = (Button)findViewById(R.id.healerBtn);
        Button btnHunter = (Button)findViewById(R.id.hunterBtn);
        Button btnPaladin = (Button)findViewById(R.id.paladinBtn);

        btnWarrior.setOnClickListener(this);
        btnMage.setOnClickListener(this);
        btnHealer.setOnClickListener(this);
        btnHunter.setOnClickListener(this);
        btnPaladin.setOnClickListener(this);

        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) !=
                Configuration.SCREENLAYOUT_SIZE_LARGE) {

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.hide(getFragmentManager().findFragmentById(R.id.statsFragment));
            fragmentTransaction.commit();

        }

    }

    @Override
    public void onClick(View v){

        //If not a large screen go to second activity
        if ((getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) !=
                Configuration.SCREENLAYOUT_SIZE_LARGE) {

            Intent intent = new Intent(this, StatsActivity.class);

            if(v.getId() == R.id.warriorBtn){
                intent.putExtra("class", "Warrior");
            }else if(v.getId() == R.id.mageBtn){
                intent.putExtra("class", "Mage");
            }else if(v.getId() == R.id.healerBtn){
                intent.putExtra("class", "Healer");
            }else if(v.getId() == R.id.hunterBtn){
                intent.putExtra("class", "Hunter");
            }else{
                intent.putExtra("class", "Paladin");
            }

            startActivity(intent);

        //Large screen view
        }else{
            //TODO: Load statsFragment values based on class button pressed

            FragmentManager mgr = getFragmentManager();
            StatsFragment statsFrag = (StatsFragment)mgr.findFragmentById(R.id.statsFragment);

            if(v.getId() == R.id.warriorBtn){
                statsFrag.loadValues("Warrior");
            }else if(v.getId() == R.id.mageBtn){
            }else if(v.getId() == R.id.healerBtn){
            }else if(v.getId() == R.id.hunterBtn){
            }else{
            }
        }
    }

    public void displayScreenSize(){
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        String toastMsg;
        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                toastMsg = "Large screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                toastMsg = "Normal screen";
                break;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                toastMsg = "Small screen";
                break;
            default:
                toastMsg = "Screen size is neither large, normal or small";
        }
        Toast.makeText(this, toastMsg, Toast.LENGTH_LONG).show();

    }

}
