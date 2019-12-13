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

import com.androiddev.termproject.dto.Yoga;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private List<Yoga> mYogas;
    private Listener listener;
    private String[] captions;
    private int[] imageIds;
    private int[] ids;

    interface Listener {
        void onClick(int position);
    }

    public RecyclerAdapter(int[]ids, String[] captions, int[] imageIds) {
        this.captions = captions;
        this.imageIds = imageIds;
        this.ids = ids;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private ViewHolder (CardView v){
            super(v);
            cardView = v;
        }
    }

    @Override
    public int getItemCount(){
        //return mYogas.size();
        return captions.length;
    }

    public void setListener(Listener listener){
        this.listener = listener;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType){
        CardView cv = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder,final int position){
        final CardView cardView = viewHolder.cardView;

        ImageView imageView = (ImageView) cardView.findViewById(R.id.info_image);
        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), imageIds[position]);
        imageView.setImageDrawable(drawable.getCurrent());
        imageView.setContentDescription(captions[position]);

        TextView textView = (TextView)cardView.findViewById(R.id.info_tittle);
        textView.setText(captions[position]);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(ids[position]);
                }
            }
        });
    }
}
