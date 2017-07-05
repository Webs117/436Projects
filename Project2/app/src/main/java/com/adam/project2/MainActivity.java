package com.adam.project2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.Button;

//Small device plan:
//Second activity for stats screen
//Use fragment to load preferences
public class MainActivity extends FragmentActivity implements View.OnClickListener {

    Fragment stats = new StatsFragment();


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


//        FragmentManager fm = getSupportFragmentManager();
//        fm.beginTransaction()
//                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//                .replace(android.R.id.content, stats)
//                .commit();

    }

    @Override
    public void onClick(View v){
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

    }

}
