package com.androiddev.termproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Button selectButtonId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        selectButtonId = (Button) findViewById(R.id.selectButton);

        selectButtonId.setOnClickListener(new View.OnClickListener(){
            @Override

            public void onClick(View v){
                Intent intent = new Intent (MainActivity.this, AppTabsActivity.class);
                startActivity(intent);
            }
        });

//        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.activity_main_relativeLayout);
//        relativeLayout.setBackgroundResource(R.drawable.background_image);
    }
}
