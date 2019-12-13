package com.androiddev.termproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.androiddev.termproject.database.project.history.HistoryDbHelper;
import com.androiddev.termproject.dto.Favorite;
import com.androiddev.termproject.dto.History;
import com.androiddev.termproject.dto.Yoga;

import java.util.ArrayList;
import java.util.List;

public class UserSelectListDetailFragment extends Fragment {

    private final static String TAG = UserSelectListDetailFragment.class.getSimpleName ();

    private TextView titleTextView;
    private TextView summaryTextView;
    private ImageView imageView;
    private TextView hyperLink;
    private EditText minuteView;
    Spanned textLink;
    private Button historyButton;


    private ListView mListView;
    private Favorite mFavorite;
    View view;



    private UserSelectListDetailFragment mThisFragment;

    public static final String FAVORITE = "favorite_object";


    public static UserSelectListDetailFragment newInstance(Favorite favorite) {
        Bundle args = new Bundle();
        args.putSerializable(FAVORITE, favorite);
        UserSelectListDetailFragment fragment = new UserSelectListDetailFragment();
        fragment.setArguments(args);

        return fragment;
    }

    public UserSelectListDetailFragment() {
        mThisFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.user_selected_list_detail_fragment, container, false);

        titleTextView = view.findViewById(R.id.user_detail_title);
        summaryTextView = view.findViewById(R.id.user_detail_summary);
        imageView = view.findViewById(R.id.user_detail_image);
        //mListView = view.findViewById(R.id.user_history_listview);
        hyperLink = view.findViewById(R.id.watch_video_user);
        minuteView = view.findViewById(R.id.input_minute_todo);



        Bundle args = getArguments();
        mFavorite = (Favorite) (args.getSerializable(FAVORITE));
        Log.d(TAG, " Yoga Id test: " + mFavorite.getId());
        setFavorite(mFavorite);


        //Set listener for History Button
        historyButton = view.findViewById(R.id.clickShowHistory);
        historyButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), HistoryActivity.class);
                myIntent.putExtra(HistoryFragment.ROUTINE_ID, mFavorite.getRoutineId());
                startActivity(myIntent);
            }
        });


       // displayHistoriesListView();
        return view;
    }

    public void setFavorite(Favorite favorite) {

        try{
            titleTextView.setText(favorite.getTitle());

            summaryTextView.setText(favorite.getSummary());

            imageView.setImageDrawable(ContextCompat.getDrawable(getContext(), favorite.getImageId()));
            textLink = Html.fromHtml(favorite.getLinks());
            hyperLink.setMovementMethod(LinkMovementMethod.getInstance());
            hyperLink.setText(textLink);

            String timeFromDefault = Integer.toString(mFavorite.getTime());
            System.out.println(timeFromDefault);
            minuteView.setText(timeFromDefault);


        }catch (SecurityException e){
            Toast toast = Toast.makeText(getActivity(), "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


    public Favorite getFavorite(){
        return mFavorite;
    }
}


