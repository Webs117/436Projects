package com.adam.memegenerator;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int IMAGE_PICKER_SELECT = 1;

    private Bitmap memeImageBitmap;
    private Uri imageURI;
    private Button btnMakeMeme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnGallery = (Button)findViewById(R.id.btnImgFrGallery);
        btnMakeMeme = (Button)findViewById(R.id.makeMemeBtn);

        btnGallery.setOnClickListener(this);
        btnMakeMeme.setOnClickListener(this);

        btnMakeMeme.setEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.settingsFab);
        fab.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        if(v.getId() == R.id.btnImgFrGallery){


            Intent intent = new Intent(Intent.ACTION_PICK,
                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

            startActivityForResult(intent, IMAGE_PICKER_SELECT);
        }else if(v.getId() == R.id.makeMemeBtn){
            Intent intent = new Intent(this, MemeActivity.class);
            intent.putExtra("MemeImageURI", imageURI.toString());

            startActivity(intent);
        }
        if(v.getId() == R.id.settingsFab){
            Intent modifySettings = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(modifySettings);
        }
    }

    // Handles gallery selection event
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == IMAGE_PICKER_SELECT  && resultCode == Activity.RESULT_OK) {
            try{
                imageURI = data.getData();

                memeImageBitmap = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());

                ImageView imageView = (ImageView) findViewById(R.id.previewImg);
                imageView.setImageBitmap(memeImageBitmap);

                btnMakeMeme.setEnabled(true);

            }catch(IOException e){
                e.printStackTrace();
            }

            Toast.makeText(this, "Resolved Image", Toast.LENGTH_LONG).show();
        }
    }
}
