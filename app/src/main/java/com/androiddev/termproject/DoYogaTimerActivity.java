package com.androiddev.termproject;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androiddev.termproject.database.project.favorite.FavoriteDbHelper;
import com.androiddev.termproject.database.project.history.HistoryDbHelper;
import com.androiddev.termproject.dto.Favorite;
import com.androiddev.termproject.dto.History;

import java.util.Date;
import java.util.Locale;

public class DoYogaTimerActivity extends AppCompatActivity {
    public static String FAVORITE_OBJECT = "favorite_object";
    public static final String DO_PLAYTIME = "do_playtime";
    private Favorite mFavorite;

    //Time canculate params
    private int hours;
    private int minutes;
    private int secs;

    private int second = 0;
    private boolean running;
    private boolean wasRunning;
    private int todoMinuteInteger;
    private int todoInSeconds;
    private int playMinuteFromFavorite;
    private History mHistory;
    private boolean wasStopped;

    private HistoryDbHelper mHistoryDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.do_yoga_timer_activity);
        Favorite mFavorite;

        if (savedInstanceState != null) {
            second = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        wasStopped = false;

        mFavorite = (Favorite) getIntent().getExtras().get(FAVORITE_OBJECT);
        todoMinuteInteger = (Integer) getIntent().getExtras().get(DO_PLAYTIME) ;
        todoInSeconds = todoMinuteInteger * 60;

        mHistoryDbHelper = HistoryDbHelper.get(getApplicationContext());

        mHistory = new History();
        mHistory.setStartDate(new Date());
        mHistory.setRoutineId(mFavorite.getRoutineId());
        runTimer();
    }

    @Override
    protected void onResume() {

        running = true; //set this for timer to run right away
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    private void runTimer(){

        final Handler handler = new Handler();
        handler.post(new Runnable(){

            @Override
            public void run(){
                TextView timeView = (TextView) findViewById(R.id.time_view);

                secs = todoInSeconds % 60;
                minutes = todoInSeconds / 60 % 60;
                hours = todoInSeconds / 60 / 60;

                String time = String.format(Locale.getDefault(),"%d:%02d:%02d",hours,minutes,secs);
                timeView.setText(time);

                if(running){
                    todoInSeconds--;
                    if (todoInSeconds < 1) {
                        running = false;
//                        if (!wasStopped) {
//
//                            saveHistory();
//                        }
                        if(!wasStopped){
                            wasStopped = true;
                            saveHistory();
                        }

                    }
                }
                handler.postDelayed(this, 1000);
            }
        });
    }

    private void saveHistory() {
        int duration = todoMinuteInteger * 60 - todoInSeconds;
        mHistory.setDuration(duration);
        mHistoryDbHelper.addRoutineToHistory(mHistory);
    }


    public void onClickStop(View view){
//        wasStopped = true;
//        saveHistory();
        if(!wasStopped){
            wasStopped = true;
            saveHistory();
        }
        Intent myIntent = new Intent(DoYogaTimerActivity.this, UserSelectedListDetailFrameActivity.class);
        myIntent.putExtra(UserSelectListDetailFragment.FAVORITE, mFavorite);
        startActivity(myIntent);
    }
}
