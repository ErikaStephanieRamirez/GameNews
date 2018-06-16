package com.ramirez.gamenews.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramirez.gamenews.R;
import com.ramirez.gamenews.repository.modelos.New;
import com.ramirez.gamenews.repository.modelos.Players;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Erika on 15/6/2018.
 */

public class AdapterPlayers extends RecyclerView.Adapter<AdapterPlayers.PlayersViewHolder> {

    public List<Players> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public static class PlayersViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView name,bio;
        ImageView imagen;

        public PlayersViewHolder(View view){
            super(view);
            cardView = view.findViewById(R.id.card_view_players);
            name = view.findViewById(R.id.nombre_players);
            bio = view.findViewById(R.id.biografiaPlayer);
            imagen = view.findViewById(R.id.imagenPlayers);
        }
    }

        public AdapterPlayers(List<Players> players, Context context){
            this.list = players;
            this.context = context;
        }

        @NonNull
        @Override
        public PlayersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.card_view_players,parent,false);
            PlayersViewHolder playersViewHolder = new PlayersViewHolder(view);
            return playersViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull PlayersViewHolder holder, final int position) {
            final Players players = list.get(position);

            holder.name.setText(players.getName());
            holder.bio.setText(players.getBiografia());

            if (!(players.getAvatar() == null)) {
                Picasso.with(context).load(players.getAvatar()).error(R.drawable.rosa).into(holder.imagen);
            } else {
                Picasso.with(context).load(R.drawable.rosa).error(R.drawable.rosa).into(holder.imagen);
            }
        }


    public void setPlayers(List<Players> _players){
        list = _players;
        notifyDataSetChanged();
    }

        @Override
        public int getItemCount() {
            if (list!=null){
                return list.size();
            }else {
                return 0;
            }
    }
}
