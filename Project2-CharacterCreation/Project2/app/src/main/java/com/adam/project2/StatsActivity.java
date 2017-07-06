package com.adam.project2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class StatsActivity extends Activity implements RatingBar.OnRatingBarChangeListener {

    String characterClass;
    SharedPreferences savedValues;
    float pointsLeft = 10;

    float previousStrengthRating;
    float previousIntelligenceRating;
    float previousWisdomRating;
    float previousDexterityRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        //For debugging
        //getSharedPreferences("SavedValues", 0).edit().clear().commit();

        savedValues = getSharedPreferences("SavedValues", MODE_PRIVATE);

        Intent intent = getIntent();

        characterClass = intent.getStringExtra("class");

        TextView title = (TextView)findViewById(R.id.Class_Title);
        title.setText(characterClass);

        RatingBar strengthBar = (RatingBar)findViewById(R.id.strengthRatingBar);
        RatingBar intelligenceBar = (RatingBar)findViewById(R.id.intelligenceRatingBar);
        RatingBar wisdomBar = (RatingBar)findViewById(R.id.wisdomRatingBar);
        RatingBar dexterityBar = (RatingBar)findViewById(R.id.dexterityRatingBar);

        previousStrengthRating = savedValues.getFloat(characterClass + "strength", 0.0f);
        previousIntelligenceRating = savedValues.getFloat(characterClass + "intelligence", 0.0f);
        previousWisdomRating = savedValues.getFloat(characterClass + "wisdom", 0.0f);
        previousDexterityRating = savedValues.getFloat(characterClass + "dexterity", 0.0f);

        //Get points remaining from saved values
        pointsLeft = pointsLeft - previousStrengthRating - previousIntelligenceRating - previousWisdomRating - previousDexterityRating;

        strengthBar.setRating(previousStrengthRating);
        intelligenceBar.setRating(previousIntelligenceRating);
        wisdomBar.setRating(previousWisdomRating);
        dexterityBar.setRating(previousDexterityRating);

        TextView pointsLeftLabel = (TextView)findViewById(R.id.displayPointsLeft);
        pointsLeftLabel.setText(Float.toString(pointsLeft));

        strengthBar.setOnRatingBarChangeListener(this);
        intelligenceBar.setOnRatingBarChangeListener(this);
        wisdomBar.setOnRatingBarChangeListener(this);
        dexterityBar.setOnRatingBarChangeListener(this);
    }
    @Override
    public void onRatingChanged(RatingBar bar, float rating, boolean fromUser){

        boolean update = false;
        float newPointsLeft = pointsLeft;
        String preferenceStr = characterClass + this.getRatingBarType(bar.toString());

        float previousValue = this.getPreviousRating(preferenceStr);

        //decreased rating from previous value
        if(previousValue > rating){
            newPointsLeft = (pointsLeft + (previousValue-rating));
            if(  newPointsLeft <= 10){
                update = true;
            }
         //Increased rating from previous value
        }else if (previousValue < rating){
            newPointsLeft = pointsLeft - (rating-previousValue);
            if(newPointsLeft >= 0){
                update = true;
            }
        }


        if(update){

            Editor editor = savedValues.edit();

            //Update SavedPreference
            editor.putFloat(preferenceStr, rating);
            editor.commit();

            setPreviousRating(preferenceStr,rating);

            pointsLeft = newPointsLeft;

            TextView pointsLeftLabel = (TextView)findViewById(R.id.displayPointsLeft);
            pointsLeftLabel.setText(Float.toString(pointsLeft));
        }else{
            Toast.makeText(getApplicationContext(), "Not enough points",
                    Toast.LENGTH_SHORT).show();

            //reset bar.
            // this causes the program to loop into this onRatingChange again. Need to cover rating = previousValue case
            bar.setRating(previousValue);

        }




    }

    public String getRatingBarType(String barTitleRaw){

        String barTitleStr = barTitleRaw.toString();
        String delims = "[/]";
        String[] barSplit = barTitleStr.split(delims);

        //remove special characters and ratingBar tag
        return  barSplit[1].replace("}", "").replace("RatingBar", "");

    }

    public float getPreviousRating(String type){
        if(type.equals(characterClass + "strength")){
            return this.previousStrengthRating;
        }else if(type.equals(characterClass + "intelligence")){
            return this.previousIntelligenceRating;
        }else if(type.equals(characterClass + "wisdom")){
            return this.previousWisdomRating;
        }else if(type.equals(characterClass + "dexterity")){
            return this.previousDexterityRating;
        }

        return 0.0f;
    }

    public void setPreviousRating(String type, float rating){
        if(type.equals(characterClass + "strength")){
            this.previousStrengthRating = rating;
        }else if(type.equals(characterClass + "intelligence")){
             this.previousIntelligenceRating = rating;
        }else if(type.equals(characterClass + "wisdom")){
             this.previousWisdomRating = rating;
        }else if(type.equals(characterClass + "dexterity")){
             this.previousDexterityRating = rating;
        }
    }
}
