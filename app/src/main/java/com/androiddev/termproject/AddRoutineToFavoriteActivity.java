package com.androiddev.termproject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.androiddev.termproject.database.project.favorite.FavoriteDbHelper;

public class AddRoutineToFavoriteActivity extends AppTabsActivity {

    public static final String EXTRA_ADDROUTINE_ID = "addRoutineId";
    public static final String EXTRA_ADDROUTINE_TIME = "addRoutineId";
    FavoriteDbHelper mFavoriteDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


        Integer addListId = (Integer) getIntent().getExtras().get(EXTRA_ADDROUTINE_ID);


    }




}
