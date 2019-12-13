package com.androiddev.termproject;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.termproject.database.project.history.HistoryDbHelper;
import com.androiddev.termproject.database.project.yoga.YogaDbHelper;
import com.androiddev.termproject.dto.History;
import com.androiddev.termproject.dto.Yoga;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    public static final String ROUTINE_ID = "routine_id";
    private List<History> mHistory;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        RecyclerView historyRecycler = (RecyclerView) inflater.inflate(
                R.layout.history_fragment, container, false);
        Bundle args = getArguments();
        int routineId = (Integer) args.getSerializable(ROUTINE_ID);
        HistoryDbHelper historyDbHelper = HistoryDbHelper.get(getContext());
        mHistory = historyDbHelper.getHistories(routineId);

        //Get name of the yogaList
        long[] date = new long[mHistory.size()];
        int[] duration = new int[mHistory.size()];

        for (int i = 0; i < mHistory.size(); i++) {
            date[i] = mHistory.get(i).getTime();
            duration[i] = mHistory.get(i).getDuration();

        }

        HistoryRecyclerAdapter recyclerAdapter = new HistoryRecyclerAdapter(date, duration);
        historyRecycler.setAdapter(recyclerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        historyRecycler.setLayoutManager(layoutManager);

        return historyRecycler;
    }
}
