package com.androiddev.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androiddev.termproject.dto.History;

public class HistoryActivity extends AppCompatActivity {

    HistoryFragment hisotyFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_activity);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //add fragments dynamically
        //create a fragment object
        hisotyFragment = new HistoryFragment();
        hisotyFragment.setArguments(getIntent().getExtras());
        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.historyContainer, hisotyFragment);
        // commit the transaction.
        transaction.commit();


        //Click on the Return Button
        Button returnButton = (Button) findViewById(R.id.clickReturnFromHistory);
        returnButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(HistoryActivity.this, AppTabsActivity.class);
                startActivity(intent);
            }
        });

    }
}
