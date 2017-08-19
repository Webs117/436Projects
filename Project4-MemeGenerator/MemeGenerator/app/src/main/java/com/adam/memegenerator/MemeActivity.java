package com.adam.memegenerator;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MemeActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme);

        Typeface impactFont = Typeface.createFromAsset(getAssets(), "fonts/impact.ttf");

        TextView topText = (TextView) findViewById(R.id.topText);
        topText.setTypeface(impactFont);

        TextView botText = (TextView) findViewById(R.id.bottomText);
        botText.setTypeface(impactFont);

        Intent intent = getIntent();
        Uri memeUri = Uri.parse(intent.getStringExtra("MemeImageURI"));
        try{
            Bitmap memeImageBitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), memeUri);

            ImageView imageView = (ImageView) findViewById(R.id.memeImg);
            imageView.setImageBitmap(memeImageBitmap);

        }catch(IOException e){
            e.printStackTrace();
        }

        Button setTextButton = (Button)findViewById(R.id.setMemeTextBtn);
        setTextButton.setOnClickListener(this);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        int intTopColor = sharedPref.getInt("topMemeTextColor", 123);
        String topHexColor = "#" + Integer.toHexString(intTopColor).substring(2);
        topText.setTextColor(Color.parseColor(topHexColor));

        int intBotColor = sharedPref.getInt("botMemeTextColor", 123);
        String botHexColor = "#" + Integer.toHexString(intBotColor).substring(2);
        botText.setTextColor(Color.parseColor(botHexColor));

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.setMemeTextBtn){
            EditText topTextEdit = (EditText) findViewById(R.id.topTextInput);
            String topText = topTextEdit.getText().toString();

            EditText botTextEdit = (EditText) findViewById(R.id.botTextInput);
            String botText = botTextEdit.getText().toString();

            if(botText != null || topText != null){
                TextView topTextView = (TextView)findViewById(R.id.topText);
                topTextView.setText(topText);

                TextView botTextView = (TextView)findViewById(R.id.bottomText);
                botTextView.setText(botText);
            }

        }
    }
}
