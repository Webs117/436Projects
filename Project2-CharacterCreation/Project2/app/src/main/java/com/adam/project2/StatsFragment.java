package com.adam.project2;



import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class StatsFragment extends Fragment implements RatingBar.OnRatingBarChangeListener {

    TextView title;

    String characterClass;
    SharedPreferences savedValues;
    float pointsLeft;

    float previousStrengthRating;
    float previousIntelligenceRating;
    float previousWisdomRating;
    float previousDexterityRating;

    TextView pointsLeftLabel;

    RatingBar strengthBar;
    RatingBar intelligenceBar;
    RatingBar wisdomBar;
    RatingBar dexterityBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_fragment, container, false);

        title = (TextView) view.findViewById(R.id.Class_Title);
        pointsLeftLabel = (TextView)view.findViewById(R.id.displayPointsLeft);

        strengthBar = (RatingBar) view.findViewById(R.id.strengthRatingBar);
        intelligenceBar = (RatingBar) view.findViewById(R.id.intelligenceRatingBar);
        wisdomBar = (RatingBar) view.findViewById(R.id.wisdomRatingBar);
        dexterityBar = (RatingBar) view.findViewById(R.id.dexterityRatingBar);


        // Inflate the layout for this fragment
        return view;
    }

    //Loads SharedPreferences
    public void loadValues(String charClass){

        title.setText(charClass);

        savedValues = this.getActivity().getSharedPreferences("SavedValues", Context.MODE_PRIVATE);

        characterClass = charClass;

        previousStrengthRating = savedValues.getFloat(characterClass + "strength", 0.0f);
        previousIntelligenceRating = savedValues.getFloat(characterClass + "intelligence", 0.0f);
        previousWisdomRating = savedValues.getFloat(characterClass + "wisdom", 0.0f);
        previousDexterityRating = savedValues.getFloat(characterClass + "dexterity", 0.0f);

        //changed to 10 since its being reloaded every button press
        pointsLeft = 10 - previousStrengthRating - previousIntelligenceRating - previousWisdomRating - previousDexterityRating;

        strengthBar.setRating(previousStrengthRating);
        intelligenceBar.setRating(previousIntelligenceRating);
        wisdomBar.setRating(previousWisdomRating);
        dexterityBar.setRating(previousDexterityRating);

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

            pointsLeftLabel.setText(Float.toString(pointsLeft));
        }else{
            Toast.makeText(getActivity(), "Not enough points",
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