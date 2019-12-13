package com.androiddev.termproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.termproject.database.project.yoga.YogaDbHelper;
import com.androiddev.termproject.dto.History;
import com.androiddev.termproject.dto.Yoga;

import java.util.Arrays;
import java.util.List;

public class YogaLectureListFragment extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase db;

    private List<Yoga> mYogas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        RecyclerView yogaRecycler = (RecyclerView) inflater.inflate(
                R.layout.yoga_lecture_list_fragment, container, false);


        YogaDbHelper yogaDbHelper = YogaDbHelper.get(getContext());
        mYogas = yogaDbHelper.getYogaRoutine();

        //Get name of the yogaList
        String[] yogaNames = new String[mYogas.size()];
        int[] yogaImages = new int[mYogas.size()];
        int[] ids = new int[mYogas.size()];

        for (int i = 0; i < mYogas.size(); i++) {
            yogaNames[i] = mYogas.get(i).getTitle();
            yogaImages[i] = mYogas.get(i).getImageId();
            ids[i] = mYogas.get(i).getId();
        }


        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ids, yogaNames, yogaImages);
        yogaRecycler.setAdapter(recyclerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        yogaRecycler.setLayoutManager(layoutManager);


        recyclerAdapter.setListener(new RecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), YogaLectureListDetailActivity.class);
                intent.putExtra(YogaLectureListDetailActivity.EXTRA_YOGA_POSITION, position);
                getActivity().startActivity(intent);
            }
        });
        return yogaRecycler;
    }


}
