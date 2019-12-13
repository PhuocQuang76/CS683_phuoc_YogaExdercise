package com.androiddev.termproject;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;


import com.androiddev.termproject.dto.History;
import com.androiddev.termproject.dto.Yoga;

import java.util.Date;
import java.util.List;

public class HistoryRecyclerAdapter extends RecyclerView.Adapter<HistoryRecyclerAdapter.ViewHolder> {

    private List<History> mHistories;
    private int[] ids;
    private long[] date;
    private int[] duration;


    public HistoryRecyclerAdapter(long[] date, int[] duration) {
        this.date = date;
        this.duration = duration;
        this.ids = ids;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        private CardView cardViewHistory;
        private ViewHolder (CardView v){
            super(v);
            cardViewHistory = v;
        }
    }


    @Override
    public int getItemCount(){
        //return mYogas.size();
        return duration.length;
    }



    @Override
    public HistoryRecyclerAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_history, parent, false);
        return new HistoryRecyclerAdapter.ViewHolder(cv);
    }


    @Override
    public void onBindViewHolder(HistoryRecyclerAdapter.ViewHolder viewHolder, final int position){
        final CardView cardView = viewHolder.cardViewHistory;
        //Yoga project = mYogas.get(position);

        TextView dateView = (TextView)cardView.findViewById(R.id.date);
        dateView.setText(new Date(date[position]).toString());
        TextView durationView = (TextView)cardView.findViewById(R.id.duration);
        durationView.setText(duration[position] + "seconds");


    }
}
