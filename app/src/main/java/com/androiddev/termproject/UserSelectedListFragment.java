package com.androiddev.termproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androiddev.termproject.database.project.favorite.FavoriteDbHelper;
import com.androiddev.termproject.dto.Favorite;

import java.util.List;

public class UserSelectedListFragment extends Fragment {
    private Cursor cursor;
    private SQLiteDatabase db;
    private int[] ids;
    FavoriteDbHelper favoriteDbHelper;
    RecyclerView favoriteRecycler;
    private Cursor favoriteCursor;      //added

   // private List<Yoga> mYogas;
    private List<Favorite> mFavorites;
    //RecyclerView favoriteRecycler;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

//        RecyclerView yogaRecycler = (RecyclerView) inflater.inflate(
//                R.layout.yoga_lecture_list_fragment, container, false);
//
//
//        YogaDbHelper yogaDbHelper = YogaDbHelper.get(getContext());
//        mYogas = yogaDbHelper.getYogaRoutine();
//
//        //Get name of the yogaList
//        String[] yogaNames = new String[mYogas.size()];
//
//        for (int i = 0; i < mYogas.size(); i++) {
//            yogaNames[i] = mYogas.get(i).getTitle();
//        }
//
//        //Get image of the yoga list
//        int[] yogaImages = new int[mYogas.size()];
//        for (int i = 0; i < mYogas.size(); i++) {
//            yogaImages[i] = mYogas.get(i).getImageId();
//        }
//
//        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(yogaNames, yogaImages);
//        yogaRecycler.setAdapter(recyclerAdapter);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        yogaRecycler.setLayoutManager(layoutManager);
//
//
//        recyclerAdapter.setListener(new RecyclerAdapter.Listener() {
//            @Override
//            public void onClick(int position) {
//                Intent intent = new Intent(getActivity(), UserSelectedListDetailFrameActivity.class);
//                intent.putExtra(UserSelectedListDetailFrameActivity.EXTRA_USER_YOGA_POSITION, position);
//                getActivity().startActivity(intent);
//            }
//        });
//        return yogaRecycler;



        favoriteRecycler = (RecyclerView) inflater.inflate(
                R.layout.yoga_lecture_list_fragment, container, false);

        FavoriteListView();

        return favoriteRecycler;
    }

    @Override
    public void onStart(){
        super.onStart();
        FavoriteListView();

//        Intent intent2 = new Intent(getActivity(), AppTabsActivity.class);
//        getActivity().startActivity(intent2);
    }

    private void FavoriteListView(){
        favoriteDbHelper = FavoriteDbHelper.get(getContext());
        mFavorites = favoriteDbHelper.getFavorites();

        //Get name of the yogaList
        String[] favoriteNames = new String[mFavorites.size()];
        int[] yogaImages = new int[mFavorites.size()];
        ids = new int[mFavorites.size()];
        for (int i = 0; i < mFavorites.size(); i++) {
            favoriteNames[i] = mFavorites.get(i).getTitle();
            yogaImages[i] = mFavorites.get(i).getImageId();
            ids[i] = mFavorites.get(i).getId();
        }

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(ids, favoriteNames, yogaImages);
        favoriteRecycler.setAdapter(recyclerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        favoriteRecycler.setLayoutManager(layoutManager);


        recyclerAdapter.setListener(new RecyclerAdapter.Listener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), UserSelectedListDetailFrameActivity.class);
                intent.putExtra(UserSelectedListDetailFrameActivity.EXTRA_USER_YOGA_POSITION, position);
//                Favorite favorite = favoriteDbHelper.getFavoriteById(position);
//                intent.putExtra(UserSelectListDetailFragment.FAVORITE, favorite);
                getActivity().startActivity(intent);



            }
        });
    }
}
