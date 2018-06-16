package com.ramirez.gamenews.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramirez.gamenews.Activitys.InfoActivity;
import com.ramirez.gamenews.R;
import com.ramirez.gamenews.repository.modelos.New;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterImages extends RecyclerView.Adapter<AdapterImages.ImagesViewHolder>{

    public List<New> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public static class ImagesViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        ImageView imagen;

        public ImagesViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.card_view_images);
            imagen = view.findViewById(R.id.imagen);
        }
    }

    public AdapterImages(Context context){
        this.context=context;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ImagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.card_view_images,parent,false);
        ImagesViewHolder imagesViewHolder = new ImagesViewHolder(view);
        return imagesViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImagesViewHolder holder,final int position) {

        if(list != null) {

            final New aNew = list.get(position);

            if (!(aNew.getCoverImage() == null)) {
                Picasso.with(context).load(aNew.getCoverImage()).error(R.drawable.rosa).into(holder.imagen);
            } else {
                Picasso.with(context).load(R.drawable.rosa).error(R.drawable.rosa).into(holder.imagen);
            }
        }
    }

    public void setNews(List<New> _new){
        list = _new;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (list == null)
            return 0;
        return list.size();
    }

}
