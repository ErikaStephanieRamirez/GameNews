package com.ramirez.gamenews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramirez.gamenews.R;
import com.ramirez.gamenews.repository.modelos.New;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.NewsViewHolder>{

    public List<New> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView titulo,subtitulo;
        ImageView imagen;
        CheckBox checkBox;

        public NewsViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.card_view);
            titulo = view.findViewById(R.id.titulo);
            subtitulo = view.findViewById(R.id.subtitulo);
            imagen = view.findViewById(R.id.imagen);
            checkBox = view.findViewById(R.id.checkbox);
        }
    }

    public AdapterNews(Context context){
        this.context=context;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.card_view_news,parent,false);
        NewsViewHolder newsViewHolder = new NewsViewHolder(view);
        return newsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder,final int position) {
        final New aNew = list.get(position);

        holder.titulo.setText(aNew.getTitle());
        holder.subtitulo.setText(aNew.getDescription());

        if (!(aNew.getCoverImage() == null)) {
            Picasso.with(context).load(aNew.getCoverImage()).error(R.drawable.rosa).into(holder.imagen);
        } else {
            Picasso.with(context).load(R.drawable.rosa).error(R.drawable.rosa).into(holder.imagen);
        }
        holder.checkBox.setChecked(false);
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
