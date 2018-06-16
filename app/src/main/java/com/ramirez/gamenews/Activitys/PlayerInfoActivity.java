package com.ramirez.gamenews.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramirez.gamenews.R;
import com.ramirez.gamenews.repository.modelos.Players;
import com.squareup.picasso.Picasso;

public class PlayerInfoActivity extends AppCompatActivity {

    private ImageView imagen;
    private TextView nombre,juego,biografia;
    private Players players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initialize();
        InformationNews();
    }

    private void initialize(){
        imagen = findViewById(R.id.Imagen_Info);
        nombre= findViewById(R.id.Titulo_Info);
        biografia = findViewById(R.id.Body_Info);
         juego = findViewById(R.id.Game_Info);
    }

    private void InformationNews(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        players = (Players) bundle.getSerializable("PlayersInformation");

        if (!(players.getAvatar() == null)) {
            Picasso.with(getApplicationContext()).load(players.getAvatar()).error(R.drawable.rosa).into(imagen);
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.rosa).error(R.drawable.rosa).into(imagen);
        }
        nombre.setText(players.getName());
        biografia.setText(players.getBiografia());
        juego.setText(players.getGame());
    }
}
