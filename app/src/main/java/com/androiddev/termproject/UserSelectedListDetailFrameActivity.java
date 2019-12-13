package com.androiddev.termproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.androiddev.termproject.database.project.favorite.FavoriteDbHelper;
import com.androiddev.termproject.database.project.yoga.YogaDbHelper;
import com.androiddev.termproject.dto.Favorite;
import com.androiddev.termproject.dto.Yoga;

import java.util.Iterator;
import java.util.List;

public class UserSelectedListDetailFrameActivity extends AppCompatActivity {

    public static final String EXTRA_USER_YOGA_POSITION = "user_yoga_list";
    private FavoriteDbHelper mFavoriteDbHelper;
    Favorite mFavorite;
    List<Favorite> mFavorites;
    private int positionId;


    //Yoga Detail
    UserSelectListDetailFragment userSelectListDetailFragment;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_selected_list_detail_frame_activity);
        mFavoriteDbHelper = FavoriteDbHelper.get(this.getApplicationContext());
        //positionId = mYogas.get(position).getId();
        positionId = getIntent().getExtras().getInt(EXTRA_USER_YOGA_POSITION);
        mFavorite = mFavoriteDbHelper.getFavoriteById(positionId);
        mFavorites = mFavoriteDbHelper.getFavorites();

        //add fragments dynamically
        //create a fragment object
        userSelectListDetailFragment = UserSelectListDetailFragment.newInstance(mFavorite);
        Bundle args = new Bundle();
        args.putSerializable(UserSelectListDetailFragment.FAVORITE, mFavorite);
        userSelectListDetailFragment.setArguments(args);

        // get the reference to the FragmentManger object
        FragmentManager fragManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction transaction = fragManager.beginTransaction();
        // add the fragment into the transaction
        transaction.add(R.id.yogaDetailContainer, userSelectListDetailFragment);
        // commit the transaction.
        transaction.commit();

    }


    //Do button
    public void clickDo(View view){
        int playtimeFormDo = 0;
            EditText todoMinute = (EditText) findViewById(R.id.input_minute_todo);
            if (todoMinute.getText().toString().trim().length() == 0){
                playtimeFormDo = 0;
            }else{
                playtimeFormDo = Integer.parseInt(todoMinute.getText().toString().trim());
            }

            Intent myIntent = new Intent(UserSelectedListDetailFrameActivity.this, DoYogaTimerActivity.class);
            myIntent.putExtra(UserSelectListDetailFragment.FAVORITE, mFavorite);
            myIntent.putExtra(DoYogaTimerActivity.DO_PLAYTIME, playtimeFormDo);
            startActivity(myIntent);
    }


    //Next Button
    public void clickNext(View view){
        Favorite nextFavorite = null;
        Iterator<Favorite> iter = mFavorites.iterator();
        for (;iter.hasNext();) {
            nextFavorite = iter.next();
            if (nextFavorite.getId() == mFavorite.getId()) {
                if (iter.hasNext()) {
                    nextFavorite = iter.next();
                } else {
                    nextFavorite = mFavorites.get(0);
                }
                mFavorite = nextFavorite;
                break;
            }
        }
        // get the reference to the FragmentManger object
        FragmentManager fragmentManager = getSupportFragmentManager();
        // get the reference to the FragmentTransaction object
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(userSelectListDetailFragment);
        userSelectListDetailFragment = UserSelectListDetailFragment.newInstance(nextFavorite);
        Bundle args = new Bundle();
        args.putSerializable(UserSelectListDetailFragment.FAVORITE, mFavorite);
        userSelectListDetailFragment.setArguments(args);
        // add the fragment into the transaction
        fragmentTransaction.add(R.id.yogaDetailContainer, userSelectListDetailFragment);
        // commit the transaction.
        fragmentTransaction.commit();
    }

    //Quit Button
    public void clickQuit(View view){
        //Intent myIntent = new Intent(UserSelectedListDetailFrameActivity.this, QuitConnectionActivity.class);
        Intent myIntent = new Intent(UserSelectedListDetailFrameActivity.this, AppTabsActivity.class);
        startActivity(myIntent);
    }

    //Delete Button
    public void clickDelete(View view){
        FavoriteDbHelper favoriteDbHelper = FavoriteDbHelper.get(getApplication());
        favoriteDbHelper.deleteFavorite(mFavorite.getId());

        Intent intent = new Intent(UserSelectedListDetailFrameActivity.this, AppTabsActivity.class);
        startActivity(intent);
    }


}

