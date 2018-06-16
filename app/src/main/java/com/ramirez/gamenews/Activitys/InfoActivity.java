package com.ramirez.gamenews.Activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.ramirez.gamenews.R;
import com.ramirez.gamenews.repository.modelos.New;
import com.squareup.picasso.Picasso;

public class InfoActivity extends AppCompatActivity {
    private ImageView imagen;
    private TextView titulo,body,game,fecha;
    private New news;
    private String failed = "vacio...";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        initialize();
        InformationNews();
    }

    private void initialize(){
        imagen = findViewById(R.id.Imagen_Info);
        titulo = findViewById(R.id.Titulo_Info);
        body = findViewById(R.id.Body_Info);
        game = findViewById(R.id.Game_Info);
        fecha = findViewById(R.id.Fecha_Info);
    }

    private void InformationNews(){
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        news = (New) bundle.getSerializable("NewsInformation");

        if (!(news.getCoverImage() == null)) {
            Picasso.with(getApplicationContext()).load(news.getCoverImage()).error(R.drawable.rosa).into(imagen);
        } else {
            Picasso.with(getApplicationContext()).load(R.drawable.rosa).error(R.drawable.rosa).into(imagen);
        }

        titulo.setText(news.getTitle());
        body.setText(news.getBody());
        game.setText(news.getGame());
        fecha.setText(news.getCreate_date());

    }

}